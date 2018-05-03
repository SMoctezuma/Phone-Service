package PhoneService;

public class FA2016_PhoneCustomer_Moctezuma {
    private String lastName;
    private String firstName;
    private String phoneNumber;
    private String address;

    public FA2016_PhoneCustomer_Moctezuma(String s) {
        //Split the given string up.
        String data[] = s.split("\\r?\\n");
        //To split first and last name and store it in seperate data points.
        String name[] = data[0].split(" ");
        firstName = name[0];
        if(1 >= name.length) {}
        else
        lastName = name[1];
        System.out.println("First name: "+ getFirstName());
        System.out.println("Last name: "+ getLastName());
        phoneNumber = data[1];
        address = data[2];
    }
    //mutators
    public String getLastName() {
        return lastName;
    }
    public String getFirstName() {
        return firstName;
    }
    public String getPhoneNumber() {
        return phoneNumber;
    }
    public String getAddress() {
        return address;
    }
    public void setLastName(String l) {
        lastName = l;
    }
    public void setFirstName(String f) {
        firstName = f;
    }
    public void setPhoneNumber(String p) {
        phoneNumber = p;
    }
    public void setAddress(String a) {
        address = a;
    }

}
