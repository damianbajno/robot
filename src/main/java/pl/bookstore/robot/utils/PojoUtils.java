package pl.bookstore.robot.utils;

import java.lang.reflect.Field;

/**
 * Created by damian on 30.03.16.
 */

public class PojoUtils {

    public static String getFieldsWithTypes(Class _class){
        final int FIRST_FIELD = 0;

        StringBuilder stringBuilder=new StringBuilder();
        Field[] declaredFields = _class.getDeclaredFields();

        for (int i = 0; i < declaredFields.length; i++) {
            if (i==1) stringBuilder.append(" not null unique");
            if (i!= FIRST_FIELD) stringBuilder.append(", ");
            stringBuilder.append(declaredFields[i].getName()+" "+declaredFields[i].getType().getSimpleName());
        }

        return stringBuilder.toString();
    }

}
