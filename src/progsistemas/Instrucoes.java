package progsistemas;

public class Instrucoes {

    public int add(int dest, int src) {
        return dest + src;
    }

    public int addi(int dest, int literal) {
        return dest + literal;
    }

    public int and(int dest, int src) {
        String d = Integer.toBinaryString(dest);
        String s = Integer.toBinaryString(src);
        String r = "";
        while (s.length() < d.length()) {
            s = "0" + s;
        }
        while (d.length() < s.length()) {
            d = "0" + d;
        }
        for (int i = 0; i < d.length(); ++i) {
            if (s.charAt(i) == 1 && d.charAt(i) == 1) {
                r = "1" + r;
            } else {
                r = "0" + r;
            }
        }
        return Integer.parseInt(r, 2);
    }

    public int andi(int dest, int literal) {
        String d = Integer.toBinaryString(dest);
        String l = Integer.toBinaryString(literal);
        String r = "";
        while (l.length() < d.length()) {
            l = "0" + l;
        }
        while (d.length() < l.length()) {
            d = "0" + d;
        }
        for (int i = 0; i < d.length(); ++i) {
            if (l.charAt(i) == 1 && d.charAt(i) == 1) {
                r = "1" + r;
            } else {
                r = "0" + r;
            }
        }
        return Integer.parseInt(r, 2);
    }

    public int cmp(int dest, int src) {
        if (dest == src) {
            return 0;
        }
        return 1;
    }

    public int cmpi(int dest, int literal) {
        if (dest == literal) {
            return 0;
        }
        return 1;
    }

    public int divs(int dest, int src) {
        return dest / src;
    }

    public int divu(int dest, int src) {
        return dest / src;
    }

    public int jmp(int dest) {
        return dest;
    }

    public int lsl(int dest, int count) {
        String r = Integer.toBinaryString(dest).substring(count);
        for (int i = 0; i < count; ++i) {
            r = r + "0";
        }
        return Integer.parseInt(r, 2);
    }

    public int lsr(int dest, int count) {
        String r = Integer.toBinaryString(dest);
        r = r.substring(0, r.length() - count);
        for (int i = 0; i < count; ++i) {
            r = "0" + r;
        }
        return Integer.parseInt(r, 2);
    }

    public int move(int src) {
        int dest = src;
        return dest;
    }

    public int muls(int dest, int src) {
        return dest * src;
    }

    public int mulu(int dest, int src) {
        return dest * src;
    }

    public int neg(int dest) {
        return -dest;
    }

    public int not(int dest) {
        String d = Integer.toBinaryString(dest);
        String r = "";
        for (int i = 0; i < d.length(); ++i) {
            if (d.charAt(i) == '1'){
                r = r + "0";
            } else {
                r = r + "1";
            }
        }
        return Integer.parseInt(r, 2);
    }

    public int or(int dest, int src) {
        String d = Integer.toBinaryString(dest);
        String s = Integer.toBinaryString(src);
        String r = "";
        while (s.length() < d.length()) {
            s = "0" + s;
        }
        while (d.length() < s.length()) {
            d = "0" + d;
        }
        for (int i = 0; i < d.length(); ++i) {
            if (s.charAt(i) == 0 && d.charAt(i) == 0) {
                r = "0" + r;
            } else {
                r = "1" + r;
            }
        }
        return Integer.parseInt(r, 2);
    }

    public int ori(int dest, int literal) {
        String d = Integer.toBinaryString(dest);
        String l = Integer.toBinaryString(literal);
        String r = "";
        while (l.length() < d.length()) {
            l = "0" + l;
        }
        while (d.length() < l.length()) {
            d = "0" + d;
        }
        for (int i = 0; i < d.length(); ++i) {
            if (l.charAt(i) == 0 && d.charAt(i) == 0) {
                r = "0" + r;
            } else {
                r = "1" + r;
            }
        }
        return Integer.parseInt(r, 2);
    }

    public int sub(int dest, int src) {
        return dest - src;
    }

    public int subi(int dest, int literal) {
        return dest - literal;
    }

}
