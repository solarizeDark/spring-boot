package ru.fedusiv.utils.email;

public interface MailsGenerator {

    String getMailForConfirm(String serverUrl, String code);

}
