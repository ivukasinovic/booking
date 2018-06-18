package XMLandSecurity.backend1.security;

import javax.validation.Validation;
import javax.validation.ValidatorFactory;

/**
 * Created by Dejan Stojkic (Smek) on 6/15/2018.
 */
public class Validator {

    private static Validator instance = null;
    private ValidatorFactory validatorFactory;
    javax.validation.Validator validator;

    private Validator() {
        validatorFactory = Validation.buildDefaultValidatorFactory();
        validator = validatorFactory.getValidator();
    }

    public static Validator getInstance() {
        if (instance == null) {
            instance = new Validator();
        }
        return instance;
    }


    public javax.validation.Validator getValidator() {
        return validator;
    }

    public static void setInstance(Validator instance) {
        Validator.instance = instance;
    }


}
