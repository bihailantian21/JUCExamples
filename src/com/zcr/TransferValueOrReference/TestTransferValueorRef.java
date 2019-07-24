package com.zcr.TransferValueOrReference;

/**
 * @author zcr
 * @date 2019/7/20-22:03
 */
public class TestTransferValueorRef {

    public void changeValue1(int age) {
        age = 30;
    }

    public void changeValue2(Person person) {
        person.setPersonName("xxx");
    }

    public void changeValue3(String str) {
        str = "xxxxxx";
    }

    public static void main(String[] args) {
        TestTransferValueorRef test = new TestTransferValueorRef();
        int age = 20;
        test.changeValue1(age);
        System.out.println("age----"+age);//20
        //age属于main方法的，然后调用方法时复印了一份传给它，然后方法把复印件给改动了
        // 我只是给你复印了一份值，原件根本没动，所以第一个age还是20

        Person person = new Person("abc");
        test.changeValue2(person);
        System.out.println("personName----"+person.getPersonName());//xxx
        //person是main的，传引用传内存地址给方法，两个引用指向了同一个地址，这时把这个地址的值改动了


        String str = "abc";
        test.changeValue3(str);
        System.out.println("str----"+str);//abc
        //str是属于main方法的，这个池子里有了abc
        //这个池子里面没有xxx，那么就重新创建一个指向它
    }
}


class Person{
    private Integer id;
    private String personName;

    public String getPersonName() {
        return personName;
    }

    public void setPersonName(String personName) {
        this.personName = personName;
    }

    public Person(String personName) {
        this.personName = personName;
    }
}
