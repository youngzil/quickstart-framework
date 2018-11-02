package org.quickstart.crypto.utils;

public class Constants {

    public static final String CONTENT = "content";

    public static final String SIGN = "sign";

    public static interface SIGN_METHOD {

        public static final String RSA = "RSA";

        public static final String SHA = "SHA";

    }

    public static interface KEY_ACQUIRE_TYPE {

        public static final String LOCAL = "local";

        public static final String REMOTE = "remote";

    }

    public static interface RSA_ENCRYPT_TYPE {

        public static final String ENCRYPT_PUBLIC = "Public";

        public static final String ENCRYPT_PRIVATE = "Private";

    }
}
