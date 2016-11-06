package com.ustodoFromV.domain;

import com.google.code.morphia.annotations.Entity;
import com.ustodoFromV.util.O;

// http://www.javabeat.net/using-morphia-java-library-for-mongodb/

@Entity
public class EntityUser extends EntityBaseUtd 
{
	private String name;
	private String passEncrypted;

	// CONSTRUCTORS / FACTORY
	public EntityUser() 
	{
		super(EntityUser.class);
	}

	public static EntityUser getInstance() {
		return new EntityUser();
	};

	public EntityUser(String name, String passEncrypted) {
		this.name = name;
		this.passEncrypted = passEncrypted;
	}
	
	// SAVE 
	public EntityUser save() {
		return (EntityUser) saveBase();
	};
	

	// GETTERS / SETTERS 
	public void setName(String name) {
		this.name = name;
	}
	public String getName() { 
		return name;
	}
	public void setPassEncrypted (String passEncrypted)	{
		this.passEncrypted = passEncrypted;
	}
	public String getPassEncrypted() { 
		return passEncrypted;
	}

	public String toString() {
		return super.toString() + "user.User:" +  " user.Name:" + this.getName() + ", user.Pass():" + this.getPassEncrypted() + ", user.Id:" + this.getId();					
	}



    // MAIN
    public static void main(String[] saArgs) {

        try
        {
            // test if I can make a new and save at the same moment
            EntityUser user = new EntityUser("ckon3", "soltan2").save();
            O.o("User:" + user);
            EntityUser e3 = (EntityUser) EntityUser.getInstance().getOne( "name", "ckon3"); // get the first
            O.o(e3.toString());
            //EntityUser e4 = (EntityUser) test(EntityBaseUtd.class);

            // to update
            // http://stackoverflow.com/questions/7271960/morphia-saves-new-entities-instead-of-update-id
            // http://stackoverflow.com/questions/6834202/why-is-the-mongodb-java-driver-morphia-prepending-a-property-twice

        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        finally
        {
            System.out.println ("done entitybase");
        }
    }







    // getall not working any more
    public static void main2(String[] sa)
    {
        try
        {

            // SAVE
            String sDate = "" + new java.util.Date();
            String name = "hhuser"+ sDate;
            String passTestInsertString = "hhpass"+ sDate;
            EntityUser user = (new EntityUser(name, passTestInsertString)).save();
            EntityUser user2 = (new EntityUser(name, passTestInsertString)).save();
            EntityUser user3 = (new EntityUser(name, passTestInsertString)).save();

            // TEST UPDATE
            EntityUser get1 = EntityUser.getInstance().getOne("name", name);
            O.o("me3.getPassEncrypted()  should NOT start with yippee I updated:" + get1.getPassEncrypted());
            get1.setPassEncrypted("yippee I updated" + sDate);
            get1.save();

            EntityUser me4 = EntityUser.getInstance().getOne("name", name);
            O.o("me4.getPassEncrypted() should start with yippee I updated:" + me4.getPassEncrypted());
            me4.setPassEncrypted("yippee I updated" + sDate);
            me4.save();

            // GETONE
            EntityUser me5 = EntityUser.getInstance().getOne("name", sDate);
            O.o("should be yippee:" + me5.toString());

            // GETALL
//            EntityUser[] arrEntityUser = getAll(EntityUser.class, "name", sDate);
//            int i = 0;
//            for (EntityUser  entityUser : arrEntityUser)
//            {
//                i++;
//                O.o (i + ". getArr result:" + entityUser.toString());
//            }
//            if (i != 3)
//                throw new RuntimeException("failed!! ");

        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        finally
        {
            System.out.println ("done utilmorphia");
        }
    }















}
