package org.soluvas.benchmarkemail;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.TreeNode;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.joda.JodaModule;
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
     * Adding contacts to an existing list
     */
    int listAddContacts(String listID, List<Contact> contacts) {
        log.info("Adding {} contacts to {} ... {}",
                contacts.size(), listID, contacts.stream().map(Contact::getEmail).toArray());
        final List<Map<String, Object>> contactMaps = contacts.stream().map(it -> {
            final JsonNode tree = MAPPER.valueToTree(it);
            try {
                final Map<String, Object> contactMap = MAPPER.treeToValue(tree, Map.class);
                return contactMap;
            } catch (JsonProcessingException e) {
                throw new BenchmarkEmailException(e, "Cannot convert %s to Map", it);
            }
        }).collect(Collectors.toList());
        final int result = bmeServices.listAddContacts(accessToken, listID, contactMaps);
        return result;
    }

}
