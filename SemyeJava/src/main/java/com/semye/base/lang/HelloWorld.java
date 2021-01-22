package com.semye.base.lang;

/**
 * Created by semye on 2017/12/30.
 */
public class HelloWorld {

    public static void main(String[] args) {
        int a = 4;
        int b = 8;
        int c = 16;
        char d = 'z';
        int z = a + d;
        byte ddd = 127;
        byte zzz = -128;


//        System.out.println(z);
//        System.out.println(a);
//        System.out.println(b);
//        System.out.println(2 * 2 * 2 * 2 * 2 * 2 * 2);

        System.out.println(2 ^ 100);
        System.out.println(Integer.toBinaryString(2));
        System.out.println(Integer.toBinaryString(100));

    }


//    private static void change(int a) {
//        a = a + 1;
//    }
//
//    public class V {
//        public String a = "1";
//
//        public void doSome() {
//            mainS();
//        }
//    }


    public static void mainS() {


        //        int a = 4 << 0;
//        int b = 4 << 1;
//        int c = 4 << 2;
//        System.out.println(a);
//        System.out.println(b);
//        System.out.println(c);
//        Carry carry3 = new Carry(100);
//        System.out.println(Carry.ad);
//        Carry carry1 = new Carry(1);
//        System.out.println(Carry.ad);
//        Carry carry2 = new Carry(2);
//        System.out.println(Carry.ad);


//        List<String> strings = new ArrayList<>();
//        strings = strings.stream()
//                .filter(String::isEmpty)
//                .sorted(String::compareToIgnoreCase)
//                .map(String::toString)
//                .collect(Collectors.toList());

//        int a = 100;
//
//        change(a);
//
//        System.out.println(a);


//        long start = System.currentTimeMillis();
//        List<String> list = new ArrayList<>();
//        for (int i = 0; i < 10000000; i++) {
//            list.add(UUID.randomUUID().toString());
//        }
//        long end = System.currentTimeMillis() - start;
//
//        System.out.println("耗时" + end + "ms");
//
//        List<String> list1 = new ArrayList<>();
//        Collections.sort(list1);
//
//        System.out.println("你好");
//        int a = 1;
//        int b = 1;
//        System.out.println(a + b);
//
//        System.out.println("HelloWorld2!");
//        System.out.println(HelloWorld2.class.getCanonicalName());
//        System.out.println(HelloWorld2.class.getName());
//        System.out.println(HelloWorld2.class.getSimpleName());
//        System.out.println(HelloWorld2.class.getClasses().length);
//        System.out.println(HelloWorld2.class.getTypeName());

//        boolean a = false;
//        boolean b = false;
//        boolean c = true;
//        boolean d = true;
//
//        System.out.println("====>" + (a ^ b));
//        System.out.println("====>" + (a ^ c));
//        System.out.println("====>" + (a ^ d));
//        System.out.println("====>" + (b ^ c));
//        System.out.println("====>" + (b ^ d));
//        System.out.println("====>" + (c ^ d));

//        int a1 = 100;
//
//        System.out.println("100的2进制====>" + Integer.toBinaryString(a1));
//
//        Integer a2 = 300;
//
//        System.out.println("300的2进制====>" + Integer.toBinaryString(a2));
//
//
//        int a3 = Integer.valueOf(Integer.toBinaryString(a1)) ^ Integer.valueOf(Integer.toBinaryString(a2));
//
//        int a4 = Integer.valueOf(Integer.toBinaryString(a2)) ^ Integer.valueOf(Integer.toBinaryString(a1));
//
//
//        System.out.println("====>" + a3);
//        System.out.println("====>" + a4);


//        UUID uuid = UUID.fromString("12134354");
//        System.out.println("===>" + uuid);


//        HashMap<String, String> map = new HashMap<>();
//        map.put("deviceId", "12323435465767efdcfdgfh");
//        map.put("token", "dasfgdhfjwrtedgfsdasfsdg");
//        map.put("firmType", "1");
//        map.put("signType", "0");
//        map.put("sign", "2345trhgfdsafdg");
//
//        System.out.println(map.toString());


//        Stack<Integer> stack = new Stack<>();
//
//
//        int aa=a/2;
//        do {
//            aa = aa / 2;
//            int c = aa % 2;
//            System.out.println(c);
//            stack.push(c);
//        } while (aa!=0);


//        B aB = new B();
//
//        System.out.println(aB.toString());
//        System.out.println(Integer.toHexString(aB.hashCode()));
//
//        int count = 1;
//        int n = 99;
//        int a = 0;
//        while (count < n) {
//            count = count * 2;
//            a++;
//        }

//        System.out.println(c);
//        System.out.println(a);


//        String obj = "dasdasda";
//        try {
//            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
//            ObjectOutputStream objectOutputStream = new ObjectOutputStream(byteArrayOutputStream);
//            objectOutputStream.writeObject(obj);
//            byteArrayOutputStream.close();
//            objectOutputStream.close();
//            System.out.println("====>" + byteArrayOutputStream.toByteArray());
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
    }


//	int num=new Random().nextInt(100);
//
//		System.out.println("系统已产生了一个随机的数字（0~99之间）,请问是多少？请在下方输入你猜的数字:");
//	Scanner s=new Scanner(System.in);
//	int number;
//	int i=0;
//		do {
//		number=s.nextInt();
//		i++;
//		if (number<num) {
//			System.out.println("你猜的数字太小了！再猜");
//		}else if (number>num) {
//			System.out.println("你猜的数字太大了！再猜");
//		} else if (number==num) {
//			System.out.println("恭喜你猜对了！");
//			System.out.println("你一共猜了"+i+"次");
//			System.out.println("你最聪明");
//
//		}
//
//	} while (number!=num);


    public static void mainTest(String[] args) {


//		String id=null;//		String shareUrl = "http://bbs.wdzj.com/wap/thread?tid=" + id;
//
//        Runnable runnable = new Runnable() {
//            @Override
//            public void run() {
//
//            }
//        };
//
//        FutureTask<String> futureTask = new FutureTask<>(runnable, "");
//        futureTask.run();


//		int x=5,i;
//
//		for (i=0;i<20;i=i+2)
//
//			x += i / 5;


//		System.out.println(shareUrl);


//		int n =4;
//
//		for (int i = 0; i < n - 1; i++) {
//			for (int x = i + 1; x < n; x++) {
//				System.out.print(" ");
//			}
//			for (int y = 0; y < (i + 1) * 2 - 1; y++) {
//				System.out.print("*");
//			}
//			System.out.println();
//		}
//		for (int i = 0; i < n; i++) {
//			for (int x = 0; x < i; x++) {
//				System.out.print(" ");
//			}
//			for (int y = i; y < 2 * n - i - 1; y++) {
//				System.out.print("*");
//			}
//			System.out.println();
//		}
    }


    // public static void main(String[] args) {
    //
    // //第一题 分析
    // Scanner Console=new Scanner(System.in); //实例化参数
    // System.out.print("请输入字符 :");
    // int input = Console.nextInt(); //获取输入内容给 input
    //
    // //%是求余
    // //&&是并且的意思 即两边条件必须都满足才行
    // //||是或的意思,即两边随便一个满足都行
    //
    // //而闰年的条件是满足下列随便一条即可
    // //第一个条件是被400整除 即 input%400==0
    // //第二个条件是被4整除但被100不整除
    //
    //
    // if(input%400==0||(input%4==0&&input%100!=0)){
    // System.out.println("输入的是闰年");
    // }else {
    // System.out.println("输入的是平年");
    // }
    // }

    //
    // public static void main(String[] args) {
    //
    // //练习题第一题
    //
    // //分析思路 要打印1-10 说明我们的循环语句要执行10次 所以我们要在while的判断中设置一个变量 他会由1慢慢增加到10
    // //即定义变量n 每次打印完数字后要他增加1 即n++； 如果这里不懂 多看书 n++ 和++n是有区别的
    //
    // int n=1;
    // while (n<11) { //当n小于10 则执行括号内的内容 即打印数字 之后n进行自加
    // //然后再判断 依次循环 当执行到第10次后 n++变成了11 就不满足 n<11这个条件了。while语句就不会再执行了
    // System.out.println(n);
    // n++;
    // }
    //
    // }

    // public static void main(String[] args) {
    //
    // // 练习题第二题 三种倒序
    //
    // int n = 10;
    // while (n > 0) {
    // System.out.println(n);
    // n--;
    // }
    //
    // }

    // public static void main(String[] args) {
    //
    // // 练习题第二题 三种倒序
    // int n=10;
    //
    // do {
    // System.out.println(n);
    // n--;
    // } while (n>0);
    //
    // }

    // public static void main(String[] args) {
    //
    // //练习题第二题 三种倒序
    //
    // // while 循环打印10到1
    // int n = 10;
    // while (n > 0) {
    // System.out.println(n);
    // n--;
    // }
    //
    // // do-while 循环打印10到1
    // int m = 10;
    // do {
    // System.out.println(m);
    // m--;
    // } while (m > 0);
    //
    // // for 循环打印10到1
    // for (int i = 10; i > 0; i--) {
    // System.out.println(i);
    // }
    //
    // }

    // public static void main(String[] args) {
    //
    // Scanner Console = new Scanner(System.in); // 实例化参数
    // System.out.print("请输入字符 :");
    // int n = Console.nextInt(); // 获取输入内容给 input
    //
    // //分析 当n=1时 阶乘为1；不计算
    // //当n=2时 阶乘为2x1=2； 计算 1次
    // // 当n=3时 阶乘为 3*2*1； 计算2次
    // //当n=4时 阶乘为4*3*2*1=24 计算3次
    //
    // int temp=n;//temp用来保存上一次计算的值
    // if (n==1) {
    // temp=1;
    // }else {
    // for (int i=n; i>1; i--) {
    // temp=temp*(i-1);
    // }
    // }
    //
    // System.out.println(temp);
    //
    // }
    // public static void main(String[] args) throws IOException {
    // // ArrayList<Integer> list=new ArrayList<Integer>();
    // // list.add(new Integer(1));
    // // list.add(new Integer(2));
    // // list.add(new Integer(3));
    // // Iterator<Integer> iterator=list.iterator();
    // // for (Integer wij:list) {
    // // System.out.println("number:"+wij);
    // //
    // // }
    // FileOutputStream outputStream=new FileOutputStream("obdasda");
    // ObjectOutputStream os=new ObjectOutputStream(outputStream);
    // os.writeObject("dasdasdada");
    // }

//    /**
//     * @param args
//     * @see Mathaa#test()
//     */
//    public static void main(String[] args) {
//
//
//        String hello = "hello world";
//
//
//        byte[] bytes = hello.getBytes();
//
//        InputStream inputStream = new ByteArrayInputStream(bytes);
//
//        FileOutputStream outputStream= null;
//        try {
//            outputStream = new FileOutputStream("abc");
//            ObjectOutputStream os=new ObjectOutputStream(outputStream);
//            os.writeObject("123456");
//        } catch (FileNotFoundException e) {
//            e.printStackTrace();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//
//    }


//    public static void sayHello(String str, long id) {
//        System.out.println(str + "===>" + id);
//    }
//
//
//    /**
//     * dsdsds
//     *
//     * @param username saa
//     */
//    public void addUser(String username) {
//
//    }
//
//
//    private void add() {
//
//    }
//
//    protected void aea() {
//
//    }


    void ads() {

    }


//    public static void mai1n(String[] args) {
//
//        try {
//
//            System.out.println(new AESCrypt().encrypt("HELLO"));
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//
////        int count = 6;
////
////        for (int i = 1; i < 100; i++) {
////            int temp = i % count;
////            int size;
////
////            if (temp != 0) {
////                size = (i / count) + 1;
////            } else {
////                size = i / count;
////            }
////            System.out.println("手机短信总条数==>" + i + "==>需要上传次数==>" + size);
////        }
//
//
////        long time = System.currentTimeMillis();
////
////        String text = "现金带打算打算大道理啊实打实大声道打算打算大大所大所多";
////
////        List<String> stringList = new ArrayList<>();
////        for (int i = 0; i < 1000; i++) {
////            stringList.add(text);
////        }
////
////        int size = stringList.size();
////        for (int i = 0; i < size; i++) {
////            if (stringList.get(i).contains("现金")) {
////                System.out.println(i);
////            }
////        }
////
////        long time2 = System.currentTimeMillis();
////
////        System.out.println(time);
////        System.out.println(time2);
////        System.out.println(time2 - time);
//    }


//    public void printa() {
//        int i = 0;
//        int[] num = new int[5];
//        Scanner sanScanner = new Scanner(System.in);
//        while (i < 5) {
//            System.out.println("请输入学生的成绩:");
//            int score = sanScanner.nextInt();
//            num[i] = score;
//            i++;
//        }
//
//        int temp = 0;
//        for (int j = 0; j < num.length - 1; j++) {//比较次数
//            for (int x = 0; x < num.length - 1 - j; x++) {
//                if (num[x] > num[x + 1]) {
//                    temp = num[x];
//                    num[x] = num[x + 1];
//                    num[x + 1] = temp;
//                }
//            }
//        }
//
//        //升序排列
//        for (int z = 0; z < num.length; z++) {
//            System.out.println(num[z]);
//        }
//    }
//
//    public void test() {
//
//    }





}


