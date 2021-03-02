package ru.nanikon.FlatCollection.utility;

import ru.nanikon.FlatCollection.arguments.AbstractArgument;
import ru.nanikon.FlatCollection.arguments.EnumArgument;
import ru.nanikon.FlatCollection.arguments.ObjectArgument;
import ru.nanikon.FlatCollection.arguments.ThrowConsumer;
import ru.nanikon.FlatCollection.exceptions.BooleanInputException;
import ru.nanikon.FlatCollection.exceptions.NotPositiveNumberException;
import ru.nanikon.FlatCollection.exceptions.ScriptException;

import java.io.IOException;
import java.util.*;

public class ArgParser {
    public static void parseObject(ObjectArgument<?> arg, Scanner scr, boolean isConsole, boolean isPartly) throws ScriptException {
        HashMap<String[], ThrowConsumer<String>> params = arg.getParams();
        arg.clear();
        String[][] fields = params.keySet().toArray(new String[0][0]);
        Arrays.sort(fields, (line1, line2) -> Integer.parseInt(line1[0]) - Integer.parseInt(line2[0]));
        ofer:
        for (String[] param : fields) {
            boolean right;
            if (isPartly) {
                do {
                    if (isConsole) {
                        System.out.println("Хотите изменить поле " + param[1] + "? Если да, введите +, иначе -");
                    }
                    String line = scr.nextLine();
                    if (line.equals("+")) {
                        break;
                    } else if(line.equals("-")) {
                        continue ofer;
                    } else {
                        if (isConsole) {
                            System.out.println("Ожидалось +/-, встречено " + line + ". Попробуйте ещё раз.");
                        } else {
                            throw new ScriptException("Ошибка в скрипте!" + "Ожидалось +/-, встречено " + line);
                        }
                    }
                } while (true);
            }
            do {
                right = true;
                if (isConsole) {
                    System.out.println(param[0] + " Введите поле " + param[1] + ". " + param[2]);
                };
                try {
                    params.get(param).accept(scr.nextLine());
                } catch (NumberFormatException e) {
                    if (isConsole) {
                        System.out.println("Ошибка! Введеная строка не является числом! Попробуйте ещё раз");
                        right = false;
                    } else {
                        throw new ScriptException("Ошибка в скрипте! Ожидалось число, а встречена строка");
                    }
                } catch (NullPointerException | IllegalArgumentException e) {
                    if (isConsole) {
                        System.out.println("Ошибка! " + e.getMessage() + " Попробуйте ещё раз.");
                        right = false;
                    } else {
                        throw new ScriptException("Ошибка в скрипте! " + e.getMessage());
                    }
                } catch (NotPositiveNumberException | BooleanInputException e) {
                    if (isConsole) {
                        System.out.println("Ошибка! " + e.getMessage() + " Попробуйте ещё раз.");
                        right = false;
                    } else {
                        throw new ScriptException("Ошибка в скрипте!" + e.getMessage());
                    }
                }
            } while(!right);
        }
        arg.setValue("");
    }

    public static void parseLine(AbstractArgument<?> arg, String line) throws IOException {
        arg.setValue(line);
    }

    public static void parseEnum(EnumArgument<?> arg, String value, Scanner scr, boolean isConsole) throws ScriptException {
        boolean right = true;
        do {
            try {
                arg.setValue(value);
                right = true;
            } catch (IllegalArgumentException | NullPointerException e) {
                if (isConsole) {
                    System.out.println("Ошибка! " + e.getMessage() + "Повторите ввод ещё раз, ");
                    System.out.println(arg.getConstants());
                    right = false;
                    value = scr.nextLine();
                } else {
                    throw new ScriptException("В скрипте обнаружена ошибка! " + e.getMessage());
                }
            }
        } while (!right);
    }
}