package com.georgeludwigtechnology.textanalyzer;


public class Params {
    public Params() {
    }

    public static void checkForRemainingOptions(String[] options) throws Exception {
        int illegalOptionsFound = 0;
        StringBuffer text = new StringBuffer();
        if (options != null) {
            for(int i = 0; i < options.length; ++i) {
                if (options[i].length() > 0) {
                    ++illegalOptionsFound;
                    text.append(options[i] + ' ');
                }
            }

            if (illegalOptionsFound > 0) {
                throw new Exception("Illegal options: " + text);
            }
        }
    }

    public static boolean getFlag(char flag, String[] options) throws Exception {
        return getFlag("" + flag, options);
    }

    public static boolean getFlag(String flag, String[] options) throws Exception {
        int pos = getOptionPos(flag, options);
        if (pos > -1) {
            options[pos] = "";
        }

        return pos > -1;
    }

    public static String getOption(char flag, String[] options) throws Exception {
        return getOption("" + flag, options);
    }

    public static String getOption(String flag, String[] options) throws Exception {
        int i = getOptionPos(flag, options);
        if (i > -1) {
            if (options[i].equals("-" + flag)) {
                if (i + 1 == options.length) {
                    throw new Exception("No value given for -" + flag + " option.");
                }

                options[i] = "";
                String newString = new String(options[i + 1]);
                options[i + 1] = "";
                return newString;
            }

            if (options[i].charAt(1) == '-') {
                return "";
            }
        }

        return "";
    }

    public static int getOptionPos(char flag, String[] options) {
        return getOptionPos("" + flag, options);
    }

    public static int getOptionPos(String flag, String[] options) {
        if (options == null) {
            return -1;
        } else {
            for(int i = 0; i < options.length; ++i) {
                if (options[i].length() > 0 && options[i].charAt(0) == '-') {
                    try {
                        Double.valueOf(options[i]);
                    } catch (NumberFormatException var4) {
                        if (options[i].equals("-" + flag)) {
                            return i;
                        }

                        if (options[i].charAt(1) == '-') {
                            return -1;
                        }
                    }
                }
            }

            return -1;
        }
    }
}
