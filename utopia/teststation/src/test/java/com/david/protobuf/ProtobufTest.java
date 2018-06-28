package com.david.protobuf;


import com.david.test.Container;
import com.googlecode.protobuf.format.JsonFormat;
import com.mex.iadx.openrtb.BidRequest;
import org.junit.Test;

import java.util.UUID;

public class ProtobufTest {
    @Test
    public void testOpenRtb() throws JsonFormat.ParseException {
        BidRequest bidRequest = BidRequest.newBuilder()
                .setId(UUID.randomUUID().toString())
                .setUser(BidRequest.User.newBuilder().setId("1").build())
                .build();
        String s = JsonFormat.printToString(bidRequest);
        System.out.println(s);
        BidRequest.Builder builder = BidRequest.newBuilder();
        JsonFormat.merge(s, builder);
        System.out.println(builder.build());
    }

    @Test
    public void testPerson() {
        Container.AddressBook addressBook = Container.AddressBook.newBuilder()
                .addPersonInfo(Container.Person.newBuilder()
                        .setEmail("test@test.com")
                        .setId(1)
                        .setName("wanghh").addPhone(Container.Person.PhoneNumber.newBuilder()
                                .setNumber("18888888888")
                                .setType(Container.Person.PhoneType.MOBILE)
                                .build()))
                .build();
        String s = JsonFormat.printToString(addressBook);
        System.err.println(s);
    }

}
