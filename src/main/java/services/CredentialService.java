package services;

import dao.CredentialsDAO;
import jakarta.inject.Inject;
import model.Credential;

public class CredentialService {
    @Inject
    private CredentialsDAO dao;

    public Credential getCredential() {
        return dao.getCredential();
    }
}
