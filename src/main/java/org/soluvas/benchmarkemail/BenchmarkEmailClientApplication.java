package org.soluvas.benchmarkemail;

import com.beust.jcommander.JCommander;
import com.beust.jcommander.Parameter;
import com.beust.jcommander.Parameters;
import com.google.common.collect.ImmutableList;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.Serializable;
import java.util.List;
import java.util.Optional;

@SpringBootApplication
public class BenchmarkEmailClientApplication implements CommandLineRunner {
    private static final Logger log = LoggerFactory.getLogger(BenchmarkEmailClientApplication.class);

    @Autowired
    private BenchmarkEmail benchmarkEmail;

    public static void main(String[] args) {
        SpringApplication.run(BenchmarkEmailClientApplication.class, args);
    }

    public static class Params implements Serializable {

    }

    @Parameters(commandDescription = "List contact lists")
    public class ListGetCommand implements Runnable {
        public static final String COMMAND = "listget";

        @Override
        public void run() {
            final String filter = "";
            final int pageNumber = 1;
            final int pageSize = 100;
            final String orderBy = "";
            final String sortOrder = "";
            log.info("Getting contact lists filter={} page={}/{} order={} {} ...",
                    filter, pageNumber, pageSize, orderBy, sortOrder);
            final List<ContactList> lists = benchmarkEmail.listGet(filter, pageNumber, pageSize, orderBy, sortOrder);
            int counter = 0;
            for (final ContactList list : lists) {
                counter++;
                System.out.println(counter + ". " + list);
            }
        }
    }

    @Parameters(commandDescription = "Add contacts to list")
    public class ListAddCommand implements Runnable {
        public static final String COMMAND = "listadd";

        @Parameter(description = "List ID, email, first name, last name")
        private List<String> params;

        @Override
        public void run() {
            final long listId = Long.valueOf(params.get(0));
            final Contact contact = new Contact();
            contact.setEmail(params.get(1));
            if (params.size() >= 3) {
                contact.setFirstName(params.get(2));
            }
            if (params.size() >= 4) {
                contact.setLastName(params.get(3));
            }
            final int result = benchmarkEmail.listAddContacts(listId, ImmutableList.of(contact), false);
            log.info("Added {}: {}", contact, result);
        }
    }

    @Parameters(commandDescription = "Get contacts of a list")
    public class ListGetContactsCommand implements Runnable {
        public static final String COMMAND = "listgetcontacts";

        @Parameter(description = "List ID")
        private List<String> params;
        @Parameter(names = "-f", description = "Filter. Show contacts where the email address contains with the filter")
        private String filter = "";
        @Parameter(names = "-p", description = "Page number. Fetch results from the given page number.")
        private int pageNumber = 1;
        @Parameter(names = "-s", description = "Page size. Number of results per page.")
        private int pageSize = 25;
        @Parameter(names = "-o", description = "Order by. Sort the results based on \"email\" or \"date\".")
        private String orderBy = "date";
        @Parameter(names = "-a", description = "Sort order. Sort the results in the \"asc\"ending or \"desc\"ending order.")
        private String sortOrder = "desc";

        @Override
        public void run() {
            final long listId = Long.valueOf(params.get(0));
            final List<Contact> contacts = benchmarkEmail.listGetContactsAllFields(listId, filter,
                    pageNumber, pageSize, orderBy, sortOrder);
            log.info("Got {} {}'s contacts: {}", contacts.size(), listId, contacts);
        }
    }

    @Override
    public void run(String... args) throws Exception {
        final Params params = new Params();
        final JCommander jc = new JCommander(params);
        final ListGetCommand listGet = new ListGetCommand();
        final ListAddCommand listAdd = new ListAddCommand();
        final ListGetContactsCommand listGetContacts = new ListGetContactsCommand();
        jc.addCommand(ListGetCommand.COMMAND, listGet);
        jc.addCommand(ListAddCommand.COMMAND, listAdd);
        jc.addCommand(ListGetContactsCommand.COMMAND, listGetContacts);
        jc.parse(args);
        switch (jc.getParsedCommand()) {
            case ListGetCommand.COMMAND:
                listGet.run();
                break;
            case ListAddCommand.COMMAND:
                listAdd.run();
                break;
            case ListGetContactsCommand.COMMAND:
                listGetContacts.run();
                break;
        }
    }
}
