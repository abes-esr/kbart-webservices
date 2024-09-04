package fr.abes.sudoc.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import fr.abes.sudoc.component.BaseXmlFunctionsCaller;
import fr.abes.sudoc.exception.IllegalPpnException;
import fr.abes.sudoc.utils.Utilitaire;
import org.springframework.jdbc.UncategorizedSQLException;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.sql.SQLRecoverableException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@Service
public class DoiService implements IIdentifiantService{
    private final BaseXmlFunctionsCaller caller;

    public DoiService(BaseXmlFunctionsCaller caller) {
        this.caller = caller;
    }

    @Override
    public boolean checkFormat(String doi) {
        String doiPattern = "10.\\d{0,15}.\\d{0,15}.+";
        return doi != null && doi.matches(doiPattern);
    }

    public List<String> getPpnFromIdentifiant(String doi) throws IllegalPpnException, IOException {
        try {
            return Collections.singletonList(caller.doiToPpn(doi));
        } catch (SQLRecoverableException ex) {
            throw new IOException("Incident technique lors de l'accès à la base de données");
        }
    }

}
