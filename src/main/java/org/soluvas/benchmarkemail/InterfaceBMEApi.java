package org.soluvas.benchmarkemail;

import java.util.List;
import java.util.Map;

public interface InterfaceBMEApi {
    /**
     * Returns the token that will be used for all the api methods
     */
    String login(String username, String password);

    /**
     * Returns the token string array
     */
    Object[] tokenGet(String userName, String password);

    /**
     * Add your own custom token
     */
    boolean tokenAdd(String userName, String password, String token);

    /**
     * Delete your token
     */
    boolean tokenDelete(String userName, String password, String token);

    /**
     * Returns the client setting info
     */
    Map<String, String> clientGetContactInfo(String token);

    /**
     * Creates a new list
     */
    String listCreate(String token, String listname);

    /**
     * Deletes the list specified by the listID
     */
    boolean listDelete(String token, String listID);

    /**
     * Retrieves the array of lists
     */
    List<Map<String, Object>> listGet(String token, String filter, int pageNumber, int pageSize, String orderBy, String sortOrder);

    /**
     * Adding contacts to an existing list
     */
    int listAddContacts(String token, String listId, List<Map<String, Object>> contacts);

    /**
     * Fetching contact data from an existing list.
     *
     * @param token A valid token for your account. To generate a token, use the login method.
     * @param listId The contact list ID from which you want to retrieve records. To get the contact lists in your account, use the listGet method.
     * @param filter Show contacts where the email address contains with the filter
     * @param pageNumber Fetch results from the given page number.
     * @param pageSize Number of results per page.
     * @param orderBy Sort the results based on "email" or "date".
     * @param sortOrder Sort the results in the "asc"ending or "desc"ending order.
     * @see #listGetContactsAllFields(String, String, String, int, int, String, String)
     */
    List<Map<String, Object>> listGetContacts(String token, String listId, String filter, int pageNumber, int pageSize, String orderBy, String sortOrder);

    /**
     * Get the contacts from the contact list, with all fields.
     *
     * @param token A valid token for your account. To generate a token, use the login method.
     * @param listId The contact list ID from which you want to retrieve records. To get the contact lists in your account, use the listGet method.
     * @param filter Show contacts where the email address contains with the filter
     * @param pageNumber Fetch results from the given page number.
     * @param pageSize Number of results per page.
     * @param orderBy Sort the results based on "email" or "date".
     * @param sortOrder Sort the results in the "asc"ending or "desc"ending order.
     * @see #listGetContacts(String, String, String, int, int, String, String)
     */
    List<Map<String, Object>> listGetContactsAllFields(String token, String listId, String filter, int pageNumber, int pageSize, String orderBy, String sortOrder);

    /**
     * listUpdateContactDetails() method definition, this method is used for updating list contact data
     */
    Map<Object, Object> listUpdateContactDetails(String token, String listID, String contactID, Map<Object, Object> contactDetails);

    /**
     * listGetContactDetails() method definition, this method is used for obtaining List Details by email
     */
    Map<Object, Object> listGetContactDetails(String token, String listID, String email);

    /**
     * listDeleteContacts() method definition, this method is used for deleting contacts in a list.
     */
    boolean listDeleteContacts(String token, String listID, String contacts);

    /**
     * listUnsubscribeContacts() method definition, this method is used for unsubscribing contacts from a list.
     */
    int listUnsubscribeContacts(String token, String listID, Object[] contacts);

    /**
     * Creating a template for mailing.
     */
    String emailCreate(String token, Map<Object, Object> emailData);

    /**
     * Creating a email address for confirmation.
     */
    String confirmEmailAdd(String token, String TargetEmailID);

    /**
     * Getting the list of confirmed email addresses
     **/
    Object[] confirmEmailList(String token);

    /**
     * Schedule Campaign to be sent
     **/
    boolean emailSchedule(String token, String emailID, String scheduleDate);

    /**
     * Get list of mail templates created for emailing
     */
    Object[] emailGet(String token, String filter, String status, int pageNumber, int pageSize, String orderBy, String sortOrder);

    /**
     * Get details of mail template by ID
     */
    Map<Object, Object> emailGetDetail(String token, String emailID);

    /**
     * Schedule Campaign to be sent Now
     */
    boolean emailSendNow(String token, String emailID);

    /**
     * Unschedule Campaign and return to draft mode
     */
    boolean emailUnSchedule(String token, String emailID);

    /**
     * Send Campaign test mails
     */
    boolean emailSendTest(String token, String emailID, String testEmail);

    /**
     * Update campaign template
     */
    boolean emailUpdate(String token, Map<Object, Object> emailData);

    /**
     * Delete campaign template
     */
    boolean emailDelete(String token, String emailID);

    /**
     * Assign email lists to campaign template
     */
    boolean emailAssignList(String token, String emailID, Object[] lists);
}
