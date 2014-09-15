//Kyle Kauck

package Data;

import java.io.Serializable;

public class DataHelper implements Serializable {

    private String mName;
    private String mPhone;
    private String mRelationship;
    private String mBirthday;

    public DataHelper(){

        mName = "";
        mPhone = "";
        mRelationship = "";
        mBirthday = "";

    }

    public DataHelper (String _name, String _phone, String _relationship, String _birthday){

        mName = _name;
        mPhone = _phone;
        mRelationship = _relationship;
        mBirthday = _birthday;

    }

    public String getName(){

        return mName;

    }

    public void setName(String _name){

        mName = _name;

    }

    public String getPhone(){

        return mPhone;

    }

    public void setPhone(String _phone){

        mPhone = _phone;

    }

    public String getRelationship(){

        return mRelationship;

    }

    public void setRelationship(String _relationship){

        mRelationship = _relationship;

    }

    public String getBirthday(){

        return mBirthday;

    }

    public void setBirthday(String _birthday){

        mBirthday = _birthday;

    }

}
