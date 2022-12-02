package cl.bci.exercise.utilities;

import lombok.Getter;

@Getter
public enum ReturnerEnum {

    NOT_EXIST (23, "Usuario no está registrado"),
    DUPLICATED (22, "Email ya está registrado"),
    EMAIL (11, "Email no cumple con el formato correcto"),
    OK (0, "Operación correcta"),
    PASSWORD (12, "Password no cumple con la expresión regular"),
    UNKNOWN (99, "Excepción no manejada");

    private final Integer code;
    private final String message;

    ReturnerEnum (Integer code, String message) {

        this.code = code;
        this.message = message;

    }

}
