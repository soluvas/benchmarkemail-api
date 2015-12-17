package org.soluvas.benchmarkemail;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Sample usage of {@link BMEApi}. Only for testing.
 */
public class BMEApiMain {
    private static final Logger log = LoggerFactory.getLogger(BMEApiMain.class);

    private final String username = "Your Benchmark Email Login"; // Put in your account username
    private final String password = "Your Benchmark Email Password"; // Put in your account password
    private String token = "";
    private String ListID = "BenchmarkEmail List ID - see listGet() method"; // put in ID of any list that you wish to use below
    private InterfaceBMEApi bmServices = null;

    public static void main(String[] args) {
        BMEApiMain obj = new BMEApiMain();
        obj.run();
    }

    private void run() {
        initialize(); // This gets the initial token thats used in all the examples below
        if (token.length() > 0) {
            // clientGetContactInfo();
            // tokenAdd();
            // tokenGet();
            // tokenDelete();
            listCreate();
            // listGet();
            // listDelete();
            // listAddContacts();
            // listGetContacts();
            // listGetContactDetails();
            // listUpdateContactDetails();
            // listDeleteContacts();
            // listUnsubscribeContacts();
            // emailCreate();
            // confirmEmailAdd();
            // confirmEmailList();
            // emailGet();
            // emailGetDetail();
            // emailSchedule();
            // emailSendNow();
            // emailUnSchedule();
            // emailSendTest();
            // emailUpdate();
            // emailAssignList();
            // emailDelete();
        } else {
            System.out.println("Failed to authenticate with BME");
        }

    }

    public void clientGetContactInfo() {

        final Map<String, String> map = bmServices.clientGetContactInfo(token);

        for (final String key : map.keySet()) {
            final String value = map.get(key);
            System.out.print(key + " = " + value + "\t");
        }

    }

    public void tokenAdd() {
        String mytoken = "adios123999";
        boolean flag = bmServices.tokenAdd(username, password, mytoken);
        System.out.print("flag = " + flag + "\t");
    }

    public void tokenDelete() {
        String mytoken = "adios123999";
        boolean flag = bmServices.tokenDelete(username, password, mytoken);
        System.out.print("flag = " + flag + "\t");
    }

    public void tokenGet() {
        Object[] mytokens = bmServices.tokenGet(username, password);
        int TokenSize = mytokens.length;
        int Counter = 0;

        for (final Object mytoken : mytokens) {
            Counter++;
            System.out.println(" Token ( " + Counter + " ) " + mytoken);
        }
        ;

    }

    public void listCreate() {
        String Mylistname = "My List Created from Java Now  !!";
        try {
            String Output = bmServices.listCreate(token, Mylistname);
            System.out.println("ListID = " + Output);
        } catch (Exception e) {
            throw new BenchmarkEmailException(e, "List by name '%s' exists", Mylistname);
        }
    }

    public void listAddContacts() {
        List<Map<String, Object>> contacts = new ArrayList<>();
        contacts.add(new HashMap());
        contacts.add(new HashMap());

        contacts.get(0).put("email", "sankey21@spideydomain.com");
        contacts.get(0).put("firstname", "Peter");
        contacts.get(0).put("lastname", "Parker");

        contacts.get(1).put("email", "sankey22@battydomain.com");
        contacts.get(1).put("firstname", "Bruce");
        contacts.get(1).put("lastname", "Wayne");

        int result = bmServices.listAddContacts(token, ListID, contacts);

        System.out.println("Result  = " + result);

    }

    public void listDelete() {
        boolean flag = bmServices.listDelete(token, ListID);
        System.out.println("Flag  = " + flag);
    }

    public void listGetContactDetails() {
        String email = "user1@battydomain.com";
        Map<Object, Object> ContactDetail = bmServices.listGetContactDetails(token, ListID, email);

        for (final Object key : ContactDetail.keySet()) {
            final Object value = ContactDetail.get(key);
            System.out.println(key + " = " + value + "(" + value.getClass().getSimpleName() + ")");
        }
        ;

    }

    public void listUpdateContactDetails() {
        String email = "user1@battydomain.com";
        Map<Object, Object> ContactDetail = bmServices.listGetContactDetails(token, ListID, email);
        String contactID = (String) ContactDetail.get("id");
        System.out.println("Contact ID = " + contactID);
        HashMap ContactDetailFinal = new HashMap();

        for (final Object key : ContactDetail.keySet()) {
            final Object value = ContactDetail.get(key);
            ContactDetailFinal.put(key, value);
        }
        ;

        ContactDetailFinal.put("Company Name", "Justice League United");
        ContactDetailFinal.put("FirstName", "Clark");
        ContactDetailFinal.put("LastName", "Kent");

        Map<Object, Object> output = bmServices.listUpdateContactDetails(token, ListID, contactID, ContactDetailFinal);

        for (final Object key : output.keySet()) {
            final Object value = output.get(key);
            System.out.println(key + " = " + value + "(" + value.getClass().getSimpleName() + ")");
        }
        ;

    }

    public void listDeleteContacts() {
        String email = "Jean.grey@xmen.com";
        Map<Object, Object> ContactDetail = bmServices.listGetContactDetails(token, ListID, email);
        StringBuffer str = new StringBuffer();
        String contactID = (String) ContactDetail.get("id");
        str.append(contactID).append(",");
        ContactDetail = bmServices.listGetContactDetails(token, ListID, "tarzan@tarzan.com");
        contactID = (String) ContactDetail.get("id");
        str.append(contactID);
        contactID = str.toString();
        boolean flag = bmServices.listDeleteContacts(token, ListID, contactID);
        System.out.println("Flag  = " + flag);
    }


    public void listUnsubscribeContacts() {
        String[] ContactAddress = new String[2];
        ContactAddress[0] = "Edgar.burroughs@wildfiresanket.com";
        ContactAddress[1] = "Bruce.banner@hulksanket.com";
        int result = bmServices.listUnsubscribeContacts(token, ListID, ContactAddress);
        System.out.println("Result  = " + result);
    }

    public void emailCreate() {
        HashMap emailDetail = new HashMap();
        emailDetail.put("fromEmail", "ash1@bmesrv.com");
        emailDetail.put("fromName", "Steve");
        emailDetail.put("emailName", "Sales Promo May 23 2012");
        emailDetail.put("replyEmail", "feedback@____.com");
        emailDetail.put("subject", "New Products launch at our store 1");
        emailDetail.put("templateContent", "<html><body> Hello World </body></html>");
        emailDetail.put("toListID", Integer.parseInt(ListID.trim()));
        emailDetail.put("scheduleDate", "1 May 2010 5:00"); /* In UTC */
        emailDetail.put("webpageVersion", true);
        emailDetail.put("permissionReminderMessage", "You are receiving this email because of your relationship with our company. Unsubscribe is available at the bottom of this email.");
        String output = bmServices.emailCreate(token, emailDetail);
        System.out.println("output  = " + output);
    }

    public void confirmEmailAdd() {
        String TargetEmailID = "user1@battydomain.com,user1@spideydomain.com";
        String Output = bmServices.confirmEmailAdd(token, TargetEmailID);

        if (Output.length() == 0) {
            System.out.println("Done  " + Output);
        } else {
            System.out.println("Could not be added " + Output);
        }
        ;

    }

    public void confirmEmailList() {
        Object[] EmailIds = bmServices.confirmEmailList(token);
        int Counter = 0;

        for (final Object Email : EmailIds) {
            final Map<String, Object> map = (Map<String, Object>) Email;

            for (final Object key : map.keySet()) {
                final Object value = map.get(key);
                System.out.print(key + " = " + value + "(" + value.getClass().getSimpleName() + ")\t");
            }
            ;

            System.out.println();
        }
        ;

    }

    public void emailGet() {
        int pageNumber = 1;
        int pageSize = 10;
        Object[] Campaigns = bmServices.emailGet(token, "", "", pageNumber, pageSize, "", "");
        int Counter = 0;

        for (final Object Campaign : Campaigns) {
            final Map<String, Object> map = (Map<String, Object>) Campaign;

            for (final Object key : map.keySet()) {
                final Object value = map.get(key);
                System.out.print(key + " = " + value + ")\t");
            }
            ;

            System.out.println();
        }
        ;
    }

    public void emailGetDetail() {

        int pageNumber = 1;
        int pageSize = 1;
        Object[] Campaigns = bmServices.emailGet(token, "", "", pageNumber, pageSize, "", "");
        int Counter = 0;
        String EmailID = "";

        if (Campaigns.length > 0) {
            Map<String, Object> map = (Map<String, Object>) Campaigns[0];
            EmailID = (String) map.get("id");
            System.out.println("EmailID = " + EmailID);
        }
        ;

        if (EmailID.length() > 0) {
            Map<Object, Object> map = bmServices.emailGetDetail(token, EmailID);

            for (final Object key : map.keySet()) {
                final Object value = map.get(key);
                System.out.println(key + " = " + value);
            }
            ;

        }
        ;

    }


    public void emailSchedule() {
        int pageNumber = 1;
        int pageSize = 1;
        Object[] Campaigns = bmServices.emailGet(token, "", "", pageNumber, pageSize, "", "");
        String EmailID = "";

        if (Campaigns.length > 0) {
            Map<String, Object> map = (Map<String, Object>) Campaigns[0];
            EmailID = (String) map.get("id");
            System.out.println("EmailID = " + EmailID);
        }
        ;

        try {
            boolean allok = bmServices.emailSchedule(token, EmailID, "14 Jul 2020 12:00");
            System.out.println(allok);
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
        ;

    }

    public void emailSendNow() {
        int pageNumber = 1;
        int pageSize = 1;
        Object[] Campaigns = bmServices.emailGet(token, "", "", pageNumber, pageSize, "", "");
        String EmailID = "";

        if (Campaigns.length > 0) {
            Map<String, Object> map = (Map<String, Object>) Campaigns[0];
            EmailID = (String) map.get("id");
            System.out.println("EmailID = " + EmailID);
        }
        ;

        try {
            boolean allok = bmServices.emailSendNow(token, EmailID);
            System.out.println(allok);
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
        ;

    }

    public void emailUnSchedule() {
        int pageNumber = 1;
        int pageSize = 1;
        Object[] Campaigns = bmServices.emailGet(token, "", "", pageNumber, pageSize, "", "");
        String EmailID = "";

        if (Campaigns.length > 0) {
            Map<String, Object> map = (Map<String, Object>) Campaigns[0];
            EmailID = (String) map.get("id");
            System.out.println("EmailID = " + EmailID);
        }
        ;

        try {
            boolean allok = bmServices.emailUnSchedule(token, EmailID);
            System.out.println(allok);
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
        ;

    }

    public void emailSendTest() {
        int pageNumber = 1;
        int pageSize = 1;
        String testEmail = "yourname@sitedomain.com";
        String EmailID = "";

        Object[] Campaigns = bmServices.emailGet(token, "", "", pageNumber, pageSize, "", "");

        if (Campaigns.length > 0) {
            Map<String, Object> map = (Map<String, Object>) Campaigns[0];
            EmailID = (String) map.get("id");
            System.out.println("EmailID = " + EmailID);
        }
        ;

        try {
            boolean allok = bmServices.emailSendTest(token, EmailID, testEmail);
            System.out.println(allok);
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
        ;

    }

    public void emailUpdate() {
        String EmailID = "";
        int pageNumber = 1;
        int pageSize = 1;

        Object[] Campaigns = bmServices.emailGet(token, "", "", pageNumber, pageSize, "", "");

        if (Campaigns.length > 0) {
            Map<String, Object> map = (Map<String, Object>) Campaigns[0];
            EmailID = (String) map.get("id");
        }
        ;

        HashMap emailUpdate = new HashMap();
        emailUpdate.put("id", EmailID);
        emailUpdate.put("fromEmail", "user1@sitedomain.com");
        emailUpdate.put("fromName", "Steve");
        emailUpdate.put("emailName", "Sales Promo May 10");
        emailUpdate.put("replyEmail", "feedback@sitedomain.com");
        emailUpdate.put("subject", "New Products launch at our store");
        emailUpdate.put("templateContent", "<html><body> Hello World </body></html>");
        emailUpdate.put("toListID", Integer.parseInt(ListID.trim()));
        emailUpdate.put("permissionReminderMessage", "You are receiving this email because of your relationship with our company. Unsubscribe is available at the bottom of this email.");

        boolean output = bmServices.emailUpdate(token, emailUpdate);
        System.out.println("output  = " + output);

    }

    public void listGet() {
        final int pageNumber = 1;
        final int pageSize = 100;
//        final List<Map<String, Object>> lists = bmServices.listGet(token, "", pageNumber, pageSize, "", "");
//        int counter = 0;
//        for (final Map<String, Object> list : lists) {
//            final Map<String, Object> map = list;
//            counter++;
//            System.out.print(counter + ". ");
//            for (final Object key : map.keySet()) {
//                final Object value = map.get(key);
//                System.out.print(key + " = " + value + " (" + value.getClass().getSimpleName() + ")\t");
//            }
//            System.out.println();
//        }
    }

    public void listGetContacts() {

        int pageNumber = 1;
        int pageSize = 2;

        Object[] Contacts = bmServices.listGetContacts(token, ListID, "", pageNumber, pageSize, "", "");
        int Counter = 0;

        for (final Object Contact : Contacts) {
            final Map<String, Object> map = (Map<String, Object>) Contact;

            for (final Object key : map.keySet()) {
                final Object value = map.get(key);
                System.out.print(key + " = " + value + "(" + value.getClass().getSimpleName() + ")\t");
            }
            ;

            System.out.println();
        }
        ;

    }

    public void emailAssignList() {
        HashMap emailLists[] = new HashMap[1];
        emailLists[0] = new HashMap();

        String EmailID = "";
        int pageNumber = 1;
        int pageSize = 2;
        String ListID = "";

        Object[] campaigns = bmServices.emailGet(token, "", "", pageNumber, pageSize, "", "");

        if (campaigns.length > 0) {
            Map<String, Object> map = (Map<String, Object>) campaigns[0];
            EmailID = (String) map.get("id");
        }

        List<Map<String, Object>> lists = bmServices.listGet(token, "", pageNumber, pageSize, "", "");
        if (lists.size() > 0) {
            HashMap Mylist = (HashMap) lists.get(0);
            ListID = (String) Mylist.get("id");
        }
        emailLists[0].put("emailID", EmailID);
        emailLists[0].put("toListID", ListID);

        boolean flag = bmServices.emailAssignList(token, EmailID, emailLists);

        System.out.println("output  = " + flag);
    }

    public void emailDelete() {
        int pageNumber = 1;
        int pageSize = 1;
        Object[] Campaigns = bmServices.emailGet(token, "", "", pageNumber, pageSize, "", "");
        String EmailID = "";

        if (Campaigns.length > 0) {
            Map<String, Object> map = (Map<String, Object>) Campaigns[0];
            EmailID = (String) map.get("id");
            System.out.println("EmailID = " + EmailID);
        }

        try {
            boolean allok = bmServices.emailDelete(token, EmailID);
            log.info("delete {}: {}", EmailID, allok);
        } catch (Exception ex) {
            throw new BenchmarkEmailException(ex.getMessage(), ex);
        }

    }

    public void initialize() {
        bmServices = BMEApi.getBMEServices();
        token = bmServices.login(username, password);
        if (token.length() > 0) {
            log.info("Logged in successfully with Token -> {}", token);
        } else {
            log.info("Failed to authenticate with BME");
        }
    }

}
