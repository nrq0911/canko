package com.canko.common;

import java.util.Collection;
import java.util.Map;

/**
 * Created by nrq on 2017/7/1.
 */
public class StringUtils {

    public static boolean isEmpty(Object obj)
    {
        if (obj == null)
            return true;

        if (obj instanceof CharSequence)
            return ((CharSequence) obj).length() == 0;

        if (obj instanceof Collection)
            return ((Collection) obj).isEmpty();

        if (obj instanceof Map)
            return ((Map) obj).isEmpty();

        if (obj instanceof Object[])
        {
            Object[] object = (Object[]) obj;
            if (object.length == 0)
            {
                return true;
            }
            boolean empty = true;
            for (int i = 0; i < object.length; i++)
            {
                if (!isEmpty(object[i]))
                {
                    empty = false;
                    break;
                }
            }
            return empty;
        }
        return false;
    }

    public static boolean isNotEmpty(Object obj)
    {
        return !isEmpty(obj);
    }

}
