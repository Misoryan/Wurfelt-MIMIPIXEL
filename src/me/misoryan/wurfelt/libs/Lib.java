package me.misoryan.wurfelt.libs;

import org.bukkit.ChatColor;

import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Lib
{

    private Lib() {
    }

    public static String getCurrentText(final String s) {
        return ChatColor.translateAlternateColorCodes('&',s);
    }

    public static String getCurrentArgsFormat(final String[] args, final int start) {
        return IntStream.range(start, args.length).mapToObj(i -> args[i] + " ").collect(Collectors.joining()).trim();
    }

    public static void execute(String s) throws IOException {
        Runtime.getRuntime().exec(s);
    }

    public static String getHashed(final String text) {
        try {
            final MessageDigest digest = MessageDigest.getInstance("md5");
            final byte[] bs = digest.digest(text.getBytes());
            String hexString = "";
            for (final byte b : bs) {
                final int temp = b & 0xFF;
                if (temp < 16 && temp >= 0) {
                    hexString = hexString + "0" + Integer.toHexString(temp);
                }
                else {
                    hexString += Integer.toHexString(temp);
                }
            }
            return hexString;
        }
        catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            return "";
        }
    }
}
