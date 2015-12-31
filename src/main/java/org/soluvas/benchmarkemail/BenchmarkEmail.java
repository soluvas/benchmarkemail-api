package org.soluvas.benchmarkemail;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.TreeNode;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.joda.JodaModule;
import com.google.common.collect.ImmutableMap;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Created by ceefour on 12/17/15.
 */
public class BenchmarkEmail {
    private static final Logger log = LoggerFactory.getLogger(BenchmarkEmail.class);
    protected static final ObjectMapper MAPPER = new ObjectMapper();

    static {
        MAPPER.registerModule(new JodaModule());
    }

    private String accessToken;
    private InterfaceBMEApi bmeServices;

    public InterfaceBMEApi getBmeServices() {
        return bmeServices;
    }

    public void setBmeServices(InterfaceBMEApi bmeServices) {
        this.bmeServices = bmeServices;
    }

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    /**
     * Retrieves the array of lists
     */
    public List<ContactList> listGet(String filter, int pageNumber, int pageSize, String orderBy, String sortOrder) {
        log.info("Getting contact lists filter={} page={}/{} order={} {} ...",
                filter, pageNumber, pageSize, orderBy, sortOrder);
        final List<Map<String, Object>> contactListMaps = bmeServices.listGet(accessToken, filter, pageNumber, pageSize, orderBy, sortOrder);
        final List<ContactList> contactLists = contactListMaps.stream().map(it -> {
            final TreeNode node = MAPPER.valueToTree(it);
            try {
                return MAPPER.treeToValue(node, ContactList.class);
            } catch (JsonProcessingException e) {
                throw new BenchmarkEmailException(e, "Cannot parse: %s", it);
            }
        }).collect(Collectors.toList());
        log.info("Got {} contact lists: {}", contactLists.size(),
                contactLists.stream().map(ContactList::getName).toArray());
        return contactLists;
    }

    /**
     * Add the contact details to the given contact list. Multiple contacts would be added if the details has more than one items.
     * http://www.benchmarkemail.com/API/Doc/listAddContacts
     *
     * @param listId The contact list ID in which to add contacts. To get all the contact lists, use the {@link #listGet(String, int, int, String, String)} method.
     * @param contacts The array containing the contact details.
     * @param optin Optional. {@code true} will send a confirmation email before adding the contact to the list.
     * @return The total number of contacts which were successfully added.
     * @see InterfaceBMEApi#listAddContacts(String, String, List, String)
     */
    public int listAddContacts(long listId, List<Contact> contacts, boolean optin) {
        log.info("Adding {} contacts to {} ... {}",
                contacts.size(), listId, contacts.stream().map(Contact::getEmail).toArray());
        final List<Map<String, Object>> contactMaps = contacts.stream().map(it -> {
            final JsonNode tree = MAPPER.valueToTree(it);
            try {
                final Map<String, Object> contactMap = MAPPER.treeToValue(tree, Map.class);
                // Email -> email, First Name -> firstname, Last Name -> lastname
                contactMap.put("firstname", contactMap.remove("First Name"));
                contactMap.put("lastname", contactMap.remove("Last Name"));
                return contactMap;
            } catch (JsonProcessingException e) {
                throw new BenchmarkEmailException(e, "Cannot convert %s to Map", it);
            }
        }).collect(Collectors.toList());
        log.debug("Adding {} contacts to {} ... {}", contacts.size(), listId, contactMaps);
        final int result = bmeServices.listAddContacts(accessToken, String.valueOf(listId), contactMaps,
                optin ? "1" : null);
        // 1: OK
        // -2: Already exists
        final ImmutableMap<Integer, String> codes = ImmutableMap.of(1, "Added", -2, "Contact(s) already exist");
        log.info("Added {} contacts to {}: {} ({})",
                contacts.size(), listId, result, codes.get(result));
        return result;
    }

    /**
     * Add the batch of contact details to the given contact list. Multiple contacts would be added if the details has more than one items.
     * http://www.benchmarkemail.com/API/Doc/batchAddContacts
     *
     * @param listId The contact list ID in which to add contacts. To get all the contact lists, use the {@link #listGet(String, int, int, String, String)} method.
     * @param contacts The array containing the contact details.
     * @return Returns the batch ID. To check the status use the batchGetStatus method.
     * @see InterfaceBMEApi#batchAddContacts(String, String, List)
     */
    public String batchAddContacts(long listId, List<Contact> contacts) {
        log.info("Batch-adding {} contacts to {} ... {}",
                contacts.size(), listId, contacts.stream().map(Contact::getEmail).toArray());
        final List<Map<String, Object>> contactMaps = contacts.stream().map(it -> {
            final JsonNode tree = MAPPER.valueToTree(it);
            try {
                final Map<String, Object> contactMap = MAPPER.treeToValue(tree, Map.class);
                // Email -> email, First Name -> firstname, Last Name -> lastname
                contactMap.put("firstname", contactMap.remove("First Name"));
                contactMap.put("lastname", contactMap.remove("Last Name"));
                return contactMap;
            } catch (JsonProcessingException e) {
                throw new BenchmarkEmailException(e, "Cannot convert %s to Map", it);
            }
        }).collect(Collectors.toList());
        log.debug("Batch-adding {} contacts to {} ... {}", contacts.size(), listId, contactMaps);
        final String batchId = bmeServices.batchAddContacts(accessToken, String.valueOf(listId), contactMaps);
        log.info("Batch-added {} contacts to {}: {}", contacts.size(), listId, batchId);
        return batchId;
    }

    public List<Contact> listGetContactsAllFields(long listId, String filter, int pageNumber, int pageSize, String orderBy, String sortOrder) {
        log.info("Get {}'s contacts all fields filter={} page={}/{} order={},{} ...",
                listId, filter, pageNumber, pageSize, orderBy, sortOrder);
        final List<Map<String, Object>> rawResults = bmeServices.listGetContactsAllFields(accessToken, Long.toString(listId), filter,
                pageNumber, pageSize, orderBy, sortOrder);
        log.debug("Got {} {}'s contacts all fields filter={} page={}/{} order={},{}: {}",
                rawResults.size(), listId, filter, pageNumber, pageSize, orderBy, sortOrder, rawResults);
        final List<Contact> contacts = rawResults.stream().map(it -> {
            final JsonNode jsonNode = MAPPER.valueToTree(it);
            try {
                final Contact contact = MAPPER.treeToValue(jsonNode, Contact.class);
                return contact;
            } catch (JsonProcessingException e) {
                throw new BenchmarkEmailException(e, "Cannot convert %s to Contact", it);
            }
        }).collect(Collectors.toList());
        return contacts;
    }

}
