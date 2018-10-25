package wangfeixixi.com.soaplib.provide;

import java.util.Random;

public class HgEncrypKey {


    public static String getHex(int num) {
        String msg = Integer.toHexString(num).toString().toUpperCase();
        if (msg.length() == 1) {
            msg = "0" + msg;
        }
        return msg;
    }


    public static String HgEncrypKey(String src) {

        Integer KeyLen;
        Integer KeyPos;
        Integer offset;
        Integer SrcPos;
        Integer SrcAsc;
        Integer Range;
        String Dest;

        String key = "KjRU%*HF%";

        KeyLen = key.length();
        if (KeyLen == 0) {
            key = "xj2006";
        }
        KeyPos = -1;
        Range = 256;
        Random random = new Random();
        int num = random.nextInt(Range);
//		num=67;
        //	System.out.println("随机数"+num);
        Dest = getHex(num);
        //	System.out.println("随机数>十六"+Dest);
        if (src == null) {
            src = "";
        }

        char[] cs = src.toCharArray();
        for (int i = 0; i < cs.length; i++) {
//			System.out.println("src字符："+(int)cs[i]);
            SrcAsc = ((int) cs[i] + num) % 255;    //ascii码值+随机数再求余
            //		System.out.println("求余结果"+SrcAsc);
            if (KeyPos < KeyLen - 1) {
                KeyPos = KeyPos + 1;
            } else {
                KeyPos = 0;
            }
            //		System.out.println("KeyPos："+KeyPos);
            int ss = (int) key.charAt(KeyPos);
//			System.out.println(ss);
            SrcAsc = SrcAsc ^ ss;    //求异或
//			System.out.println("异或结果"+SrcAsc);
            Dest = Dest + getHex(SrcAsc);
            num = SrcAsc;
        }
        return Dest;
    }


    /**
     * 解密
     * function UncrypKey(Src: Pchar; Key: Pchar): String;
     * var
     * KeyLen: Integer;
     * KeyPos: Integer;
     * offset: Integer;
     * dest, StringSrc, StringKey: string;
     * SrcPos: Integer;
     * SrcAsc: Integer;
     * TmpSrcAsc: Integer;
     * begin
     * StringSrc := StrPas(Src);
     * StringKey := StrPas(Key);
     * <p>
     * if Length(StringSrc) = 2 then
     * begin
     * Result := EmptyStr;
     * Exit;
     * end;
     * <p>
     * KeyLen := Length(StringKey);
     * if KeyLen = 0 then StringKey := 'xj2006';
     * KeyPos := 0;
     * try
     * offset := StrToInt('$' + copy(StringSrc, 1, 2));
     * except
     * offset := 0;
     * end;
     * <p>
     * SrcPos := 3;
     * repeat
     * try
     * SrcAsc := StrToInt('$' + copy(StringSrc, SrcPos, 2));
     * Except
     * SrcAsc := 0;
     * end;
     * <p>
     * if KeyPos < KeyLen Then KeyPos := KeyPos + 1 else KeyPos := 1;
     * TmpSrcAsc := SrcAsc xor Ord(StringKey[KeyPos]);
     * if TmpSrcAsc <= offset then
     * TmpSrcAsc := 255 + TmpSrcAsc - offset
     * else
     * TmpSrcAsc := TmpSrcAsc - offset;
     * dest := dest + chr(TmpSrcAsc);
     * offset := srcAsc;
     * SrcPos := SrcPos + 2;
     * until SrcPos >= Length(StringSrc);
     * Result := Dest;
     * end;
     */


    public static String HgUnEncrypKey(String src) {
        Integer KeyLen;
        Integer KeyPos;
        Integer offset;
        String dest = "";
        String StringSrc;
        String StringKey;
        Integer SrcPos;
        Integer SrcAsc;
        Integer TmpSrcAsc;

        StringSrc = src;
        StringKey = "ADDBYHGFFOVER";

        if (StringSrc.length() == 2) {
            return null;
        }
        KeyLen = StringKey.length();
        if (KeyLen == 0) {
            StringKey = "xj2006";
        }

        KeyPos = -1;
        try {
            offset = Integer.valueOf(StringSrc.substring(0, 2), 16).intValue();
        } catch (Exception e) {
            offset = 0;
        }
//		System.out.println("offset:"+offset);
        SrcPos = 3;
        do {
            try {
                String s = StringSrc.substring(SrcPos - 1, SrcPos + 1);
//				System.out.println(s);
                SrcAsc = Integer.valueOf(s, 16).intValue();

            } catch (Exception e) {
                SrcAsc = 0;
            }
//			System.out.println("SrcAsc:"+SrcAsc);
            if (KeyPos < KeyLen - 1) {
                KeyPos = KeyPos + 1;
            } else {
                KeyPos = 0;
            }

            int ss = (int) StringKey.charAt(KeyPos);
//		    System.out.println("ss:"+ss);
            TmpSrcAsc = SrcAsc ^ ss;    //求异或
//		    System.out.println("TmpSrcAsc:"+TmpSrcAsc);
            if (TmpSrcAsc <= offset) {
                TmpSrcAsc = 255 + TmpSrcAsc - offset;
            } else {
                TmpSrcAsc = TmpSrcAsc - offset;
            }
//		    System.out.println("TmpSrcAsc:"+TmpSrcAsc);
            char d = (char) ((int) TmpSrcAsc);
            dest = dest + d;
//	    	System.out.println("dest::"+dest);
            offset = SrcAsc;
            SrcPos = SrcPos + 2;
        } while (!(SrcPos >= StringSrc.length()));
//		System.out.println("dest::"+dest);
        return dest;
    }


    /**
     * 获取字符串截取
     * <p>
     * function getEncryptStr(aCmdText: String): String;
     * const
     * ArrayIdx: array[0..7] of Integer = (6, 1, 4, 8, 15, 10, 2, 50);
     * var
     * i: Integer;
     * iLen: Integer;
     * begin
     * Result := EmptyStr;
     * iLen := Length(aCmdText);
     * for i := Low(ArrayIdx) to High(ArrayIdx) do
     * begin
     * if ArrayIdx[i] < iLen then
     * Result := Result + aCmdText[i];
     * end;
     * end;
     */

    public static String getEncryptStr(String aCmdText) {
        String Result = "";
        int ArrayIdx[] = {6, 1, 4, 8, 15, 10, 2, 50};
        for (int i = 0; i < ArrayIdx.length; i++) {
            if (ArrayIdx[i] < aCmdText.length()) {
                Result = Result + aCmdText.charAt(ArrayIdx[i] - 1);
            }
        }
        return Result;
    }


    /**
     * @param args
     */
    public static void main(String[] args) {
//		System.out.println(HgEncrypKey("KjRU%*HF%cmwc6nxq"));
//		System.out.println(HgUnEncrypKey("020C32C05420020D157CA181F174EA20"));
        System.out.println(getEncryptStr("select * from st_user"));
//		char s=(char)67;
//		System.out.println(s);
    }

}
