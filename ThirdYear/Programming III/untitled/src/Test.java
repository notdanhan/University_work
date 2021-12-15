import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;

public class Test {
    public static void main(String[] args) {
        String str1 = "zz";
        String str2 = "vö";
        System.out.println(str1.hashCode());
        System.out.println(str2.hashCode());
        HashMap<String, Integer> myHashMap = new HashMap<String, Integer>();
        myHashMap.put(str1,1);
        myHashMap.put(str2,2);
        System.out.println(myHashMap.get(str1));
    }
}
