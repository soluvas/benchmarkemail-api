package org.soluvas.benchmarkemail;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonSetter;
import com.google.common.base.Strings;
import org.joda.time.LocalDate;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

import java.io.Serializable;
import java.util.Locale;

/**
 * Note: "First Name" and "Last Name" are special, it's their official field names, however
 * in some APIs "firstname" and "lastname" is used.
 *
 * From {@link InterfaceBMEApi#listGetContactsAllFields(String, String, String, int, int, String, String)}:
 * Zip=, Address=, First Name=Hendy, Date 1=04/30/1991, Middle Name=, ip_signup=,
 * Job Title=, Business Phone=, Business City=, Phone=, Business Address=, ip_opt=, id=540932690, Business Zip=, Notes=,
 * email=x@x.com, Business State=, timestamp=Dec 18, 2015, Business Fax=, emailtype=2,
 * Extra 3=Pria, City=, Extra 6=, Extra 5=, Extra 4=, Company Name=, Business Country=, State=, Date 2=, Country=,
 * Cell Phone=, Fax=, Last Name=Irawan, status=Normal
 *
 * From https://wordpress.org/plugins/benchmark-email-lite/ - lib/class.widget.php :
 *
 'First Name',
 'Last Name',
 'Middle Name',
 'Address',
 'City',
 'State',
 'Zip',
 'Country',
 'Phone',
 'Fax',
 'Cell Phone',
 'Company Name',
 'Job Title',
 'Business Phone',
 'Business Fax',
 'Business Address',
 'Business City',
 'Business State',
 'Business Zip',
 'Business Country',
 'Notes',
 'Extra 3',
 'Extra 4',
 'Extra 5',
 'Extra 6',

 * Created by ceefour on 12/17/15.
 */
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class Contact implements Serializable {

    public static final DateTimeFormatter DATE12_FORMAT = DateTimeFormat.forPattern("MM/dd/yyyy").withLocale(Locale.US);
    public static final DateTimeFormatter TIMESTAMP_FORMAT = DateTimeFormat.forPattern("MMM dd, yyyy").withLocale(Locale.US);

    private Integer sequence;
    private String id;
    private String email;
    @JsonProperty("ip_opt")
    private String ipOpt;
    @JsonProperty("ip_signup")
    private String ipSignup;
    @JsonProperty("emailtype")
    private Byte emailType;
    @JsonProperty("status")
    private String status;
    @JsonProperty("timestamp")
    private LocalDate timestamp;

    @JsonProperty("First Name")
    private String firstName;
    @JsonProperty("Last Name")
    private String lastName;
    @JsonProperty("Middle Name")
    private String middleName;
    @JsonProperty("Address")
    private String address;
    @JsonProperty("City")
    private String city;
    @JsonProperty("State")
    private String state;
    @JsonProperty("Zip")
    private String zip;
    @JsonProperty("Country")
    private String country;
    @JsonProperty("Phone")
    private String phone;
    @JsonProperty("Fax")
    private String fax;
    @JsonProperty("Cell Phone")
    private String cellPhone;
    @JsonProperty("Company Name")
    private String companyName;
    @JsonProperty("Job Title")
    private String jobTitle;
    @JsonProperty("Business Phone")
    private String businessPhone;
    @JsonProperty("Business Fax")
    private String businessFax;
    @JsonProperty("Business Address")
    private String businessAddress;
    @JsonProperty("Business City")
    private String businessCity;
    @JsonProperty("Business State")
    private String businessState;
    @JsonProperty("Business Zip")
    private String businessZip;
    @JsonProperty("Business Country")
    private String businessCountry;
    @JsonProperty("Notes")
    private String notes;
    @JsonProperty("Date 1")
    private LocalDate date1;
    @JsonProperty("Date 2")
    private LocalDate date2;
    @JsonProperty("Extra 3")
    private String extra3;
    @JsonProperty("Extra 4")
    private String extra4;
    @JsonProperty("Extra 5")
    private String extra5;
    @JsonProperty("Extra 6")
    private String extra6;

    public Contact() {
    }

    public Contact(String email, String firstName, String lastName) {
        this.email = email;
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public String getIpOpt() {
        return ipOpt;
    }

    public void setIpOpt(String ipOpt) {
        this.ipOpt = ipOpt;
    }

    public String getIpSignup() {
        return ipSignup;
    }

    public void setIpSignup(String ipSignup) {
        this.ipSignup = ipSignup;
    }

    public Byte getEmailType() {
        return emailType;
    }

    public void setEmailType(Byte emailType) {
        this.emailType = emailType;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public LocalDate getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(LocalDate timestamp) {
        this.timestamp = timestamp;
    }

    @JsonSetter
    public void setTimestamp(String timestamp) {
        this.timestamp = TIMESTAMP_FORMAT.parseLocalDate(timestamp);
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Integer getSequence() {
        return sequence;
    }

    public void setSequence(Integer sequence) {
        this.sequence = sequence;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getMiddleName() {
        return middleName;
    }

    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getZip() {
        return zip;
    }

    public void setZip(String zip) {
        this.zip = zip;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getFax() {
        return fax;
    }

    public void setFax(String fax) {
        this.fax = fax;
    }

    public String getCellPhone() {
        return cellPhone;
    }

    public void setCellPhone(String cellPhone) {
        this.cellPhone = cellPhone;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getJobTitle() {
        return jobTitle;
    }

    public void setJobTitle(String jobTitle) {
        this.jobTitle = jobTitle;
    }

    public String getBusinessPhone() {
        return businessPhone;
    }

    public void setBusinessPhone(String businessPhone) {
        this.businessPhone = businessPhone;
    }

    public String getBusinessFax() {
        return businessFax;
    }

    public void setBusinessFax(String businessFax) {
        this.businessFax = businessFax;
    }

    public String getBusinessAddress() {
        return businessAddress;
    }

    public void setBusinessAddress(String businessAddress) {
        this.businessAddress = businessAddress;
    }

    public String getBusinessCity() {
        return businessCity;
    }

    public void setBusinessCity(String businessCity) {
        this.businessCity = businessCity;
    }

    public String getBusinessState() {
        return businessState;
    }

    public void setBusinessState(String businessState) {
        this.businessState = businessState;
    }

    public String getBusinessZip() {
        return businessZip;
    }

    public void setBusinessZip(String businessZip) {
        this.businessZip = businessZip;
    }

    public String getBusinessCountry() {
        return businessCountry;
    }

    public void setBusinessCountry(String businessCountry) {
        this.businessCountry = businessCountry;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public String getExtra3() {
        return extra3;
    }

    public void setExtra3(String extra3) {
        this.extra3 = extra3;
    }

    public String getExtra4() {
        return extra4;
    }

    public void setExtra4(String extra4) {
        this.extra4 = extra4;
    }

    public String getExtra5() {
        return extra5;
    }

    public void setExtra5(String extra5) {
        this.extra5 = extra5;
    }

    public String getExtra6() {
        return extra6;
    }

    public void setExtra6(String extra6) {
        this.extra6 = extra6;
    }

    public LocalDate getDate1() {
        return date1;
    }

    public void setDate1(LocalDate date1) {
        this.date1 = date1;
    }

    @JsonSetter
    public void setDate1(String date1) {
        this.date1 = !Strings.isNullOrEmpty(date1) ? DATE12_FORMAT.parseLocalDate(date1) : null;
    }

    public LocalDate getDate2() {
        return date2;
    }

    public void setDate2(LocalDate date2) {
        this.date2 = date2;
    }

    @JsonSetter
    public void setDate2(String date2) {
        this.date2 = !Strings.isNullOrEmpty(date2) ? DATE12_FORMAT.parseLocalDate(date2) : null;
    }

    @Override
    public String toString() {
        return "Contact{" +
                "sequence=" + sequence +
                ", id='" + id + '\'' +
                ", email='" + email + '\'' +
                ", ipOpt='" + ipOpt + '\'' +
                ", ipSignup='" + ipSignup + '\'' +
                ", emailType=" + emailType +
                ", status='" + status + '\'' +
                ", timestamp=" + timestamp +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", middleName='" + middleName + '\'' +
                ", address='" + address + '\'' +
                ", city='" + city + '\'' +
                ", state='" + state + '\'' +
                ", zip='" + zip + '\'' +
                ", country='" + country + '\'' +
                ", phone='" + phone + '\'' +
                ", fax='" + fax + '\'' +
                ", cellPhone='" + cellPhone + '\'' +
                ", companyName='" + companyName + '\'' +
                ", jobTitle='" + jobTitle + '\'' +
                ", businessPhone='" + businessPhone + '\'' +
                ", businessFax='" + businessFax + '\'' +
                ", businessAddress='" + businessAddress + '\'' +
                ", businessCity='" + businessCity + '\'' +
                ", businessState='" + businessState + '\'' +
                ", businessZip='" + businessZip + '\'' +
                ", businessCountry='" + businessCountry + '\'' +
                ", notes='" + notes + '\'' +
                ", date1=" + date1 +
                ", date2=" + date2 +
                ", extra3='" + extra3 + '\'' +
                ", extra4='" + extra4 + '\'' +
                ", extra5='" + extra5 + '\'' +
                ", extra6='" + extra6 + '\'' +
                '}';
    }
}
