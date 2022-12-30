package edu.school21.reflection.app;

import org.reflections.Reflections;
import org.reflections.scanners.SubTypesScanner;
import java.lang.reflect.*;
import java.util.*;
import java.util.stream.Collectors;

public class Program {

    private static Object object;
    public static void main(String[] args) throws InvocationTargetException, IllegalAccessException, NoSuchMethodException, NoSuchFieldException, InstantiationException {

        System.out.println("Classes:");
        List<Class<?>> lst = getAllClassesFrom("edu.school21.reflection.models");
        Class current = null;
        for (int i = 1; i < lst.size(); i++)
            System.out.println(lst.get(i).getSimpleName());
        Scanner input = new Scanner(System.in);

        System.out.println("---------------------\n" +
                "Enter class name: ");
        String str = input.next();
        for (int i = 1; i < lst.size(); i++)
            if (str.equals(lst.get(i).getSimpleName()))
                current = lst.get(i);

        if (current == null)
            System.exit(-1);



        Constructor constructor = current.getConstructor();
        Field[] fieldList = current.getDeclaredFields();
        System.out.println("---------------------\n" +"fields :");
        for (Field f: fieldList) {
            System.out.println(printType(f.getAnnotatedType()) + " " + f.getName());
            f.setAccessible(true);
        }

        System.out.println("---------------------\n" +"methods :");
        for (Method f: current.getDeclaredMethods())
        {
            System.out.print(printType(f.getAnnotatedReturnType()) + " " + f.getName() + "(");
            for (int i = 0; i < f.getParameters().length; i++)
            {
                System.out.print(printType(f.getParameters()[i].getAnnotatedType()));
                if (f.getParameters().length > 1 && i < f.getParameters().length - 1)
                    System.out.print(",");
            }
            System.out.println(")");
        }

        System.out.println("---------------------\n" +
                "Let's create an object. ");
        object = current.newInstance();

        for(Field field:fieldList)
        {
            System.out.println(field.getName() + ": ");
            decField(object, field, input.next());
        }
        System.out.println(object.toString());


        System.out.println("---------------------\n" +
                "Enter name of the field for changing:");
        String fieldInName = input.next();
        System.out.println("Enter " + printType(getField(object, fieldInName).getGenericType()) + " value:");
        String value = input.next();
        decField(object, getField(object, fieldInName), value);
        System.out.println(object);

        System.out.println("---------------------\n" +
                "Enter name of the method for call:");
        String methodInName = input.next();
        decMethod(object, getMethod(object, methodInName));
        System.out.println(object);
    }

    private static String printType(Object typer)
    {
        String type = typer.toString();
        if (type.contains("."))
            type = type.substring(type.lastIndexOf(".") + 1);
        return type;
    }


    private static void decMethod(Object object, Method field) throws InvocationTargetException, IllegalAccessException {
        Scanner s = new Scanner(System.in);
        field.setAccessible(true);
        Parameter[] p = field.getParameters();
        Object[] result = new Object[field.getParameters().length];
        for (int i = 0; i < field.getParameters().length; i++)
        {
            System.out.print("Enter " + printType(field.getParameters()[i].getAnnotatedType()) + " value:");
            result[i] = getValtype(field.getParameters()[i].getAnnotatedType().toString(), s.next());
        }
        System.out.println("Method returned: ");
        System.out.println(field.invoke(object, result));
    }
    private static Field getField(Object object, String fieldInName)
    {
        Field[] fieldList = object.getClass().getDeclaredFields();
        for (Field f: fieldList) {
            if (f.getName().contains(fieldInName))
                return f;
        }
        return null;
    }

    private static Method getMethod(Object object, String fieldInName)
    {
        Method[] methodList = object.getClass().getDeclaredMethods();
            for(Method method : methodList) {
                if (method.getName().equals(fieldInName.substring(0, fieldInName.indexOf('(')))) {
                    return method;
                }
            }
        System.out.println("---------------------\n" +"fields :");
        for (Method f: methodList) {
            if (f.getName().contains(fieldInName))
                return f;
        }
        return null;
    }


    private static Object getValtype(String fieldName, String value)
    {
        if (fieldName.contains("String")) {
            return value;
        } else if (fieldName.contains("int") || fieldName.contains("Integer")) {
            return Integer.parseInt(value);
        } else if (fieldName.contains("boolean") || fieldName.contains("Boolean")) {
            return  Boolean.parseBoolean(value);
        } else if (fieldName.contains("double") || fieldName.contains("Double")) {
            return Double.parseDouble(value);
        } else if (fieldName.contains("long") || fieldName.contains("Long")) {
            return  Long.parseLong(value);
        } else {
            System.out.println("UNKNOWN PARAMETER TYPE");
            System.exit(-1);
        }
        return value;
    }
    public static void decField(Object object, Field field, String value) throws IllegalAccessException {
        String fieldName;
        if (field == null)
        {
            System.out.println("Field not found");
            System.exit(-1);
        }
        field.setAccessible(true);
        fieldName = field.getGenericType().getTypeName();
//        String value = input.next();
        if (fieldName.contains("String")) {
            field.set(object, value);
        } else if (fieldName.contains("int") || fieldName.contains("Integer")) {
            field.set(object, Integer.parseInt(value));
        } else if (fieldName.contains("boolean") || fieldName.contains("Boolean")) {
            field.set(object, Boolean.parseBoolean(value));
        } else if (fieldName.contains("double") || fieldName.contains("Double")) {
            field.set(object, Double.parseDouble(value));
        } else if (fieldName.contains("long") || fieldName.contains("Long")) {
            field.set(object, Long.parseLong(value));
        } else {
            System.out.println("UNKNOWN PARAMETER TYPE");
            System.exit(-1);
        }
    }


    private static List<Class<?>> getAllClassesFrom(String packageName) {
        return new Reflections(packageName, new SubTypesScanner(false))
                .getAllTypes()
                .stream()
                .map(name -> {
                    try {
                        return Class.forName(name);
                    } catch (ClassNotFoundException e) {
                        return null;
                    }
                })
                .filter(Objects::nonNull)
                .collect(Collectors.toList());
    }
}