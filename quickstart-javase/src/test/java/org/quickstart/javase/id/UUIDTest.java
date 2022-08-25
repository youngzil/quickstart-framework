package org.quickstart.javase.id;

import org.junit.jupiter.api.Test;

import java.util.UUID;

public class UUIDTest {

    @Test
    public void testUUIDV4(){
        UUID uuid = UUID.randomUUID();

        System.out.println("uuid=" + uuid);
    }
}
