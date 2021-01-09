package data;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.Random;

public class Test01 {

        //下面是循环1000次并插入数据库的
        public static void main(String[] args) throws Exception {
            // WebDriver webDriver = new ChromeDriver();
            // // 新建Actions类,声明一个动作
            // Actions action = new Actions(webDriver);
            // Login.login(webDriver, action);
            Connection conn = JdbcUtil.getConnection();

            String[] names = {"a", "b", "c", "d", "e", "f", "g", "h", "i", "j",
                    "k", "l", "m", "n", "o", "p", "q", "r", "s", "t", "u", "v",
                    "w", "s", "y", "z"};

            String[] pw = {"a", "b", "c", "d", "e", "f", "g", "h", "i", "j",
                    "k", "l", "m", "n", "o", "p", "q", "r", "s", "t", "u", "v",
                    "w", "s", "y", "z", "1", "2", "3", "4", "5", "6", "7", "8", "9", "0"};

            String[] sex = {"male", "female"};


            String[] phones = {"13", "15", "16", "17", "18", "19"};


            int random = Test01.getRandom(10000, 99999);

            Random r = new Random(12);

            for (int i = 0; i < 10000; i++) {


                //账号
                int userLength = (int) (Math.random() * 8);

                if (userLength < 4) {
                    userLength = 4;
                }

                String name = new String();
                for (int j = 0; j < userLength; j++)
                    name = name + names[(int) (Math.random() * names.length)];

                //密码

                int pwLength = (int) (Math.random() * 15);

                if (pwLength < 6) {
                    pwLength = 6;
                }

                String password = new String();
                for (int j = 0; j < pwLength; j++)
                    password = password + pw[(int) (Math.random() * pw.length)];

                //手机号
                String phone = phones[(int) (Math.random() * 5)]
                        + (int) (Math.random() * 10) + random
                        + (int) (Math.random() * 10) + (int) (Math.random() * 10)
                        + (int) (Math.random() * 10);

                String age = String.valueOf(r.nextInt(70));

                while (!Policy.dispose2(phone)) {
                    phone = phones[(int) (Math.random() * 5)]
                            + (int) (Math.random() * 10) + random
                            + (int) (Math.random() * 10) + (int) (Math.random() * 10)
                            + (int) (Math.random() * 10);
                }


                String sex1 = sex[(int) (Math.random() * 2)];


                System.out.println("姓名：" + name.toString() + "  密码:" + password.toString()+ "   性别：" + sex1.toString() + "   年龄：" + age.toString() + "    电话：" + phone.toString());
                String sql = "insert into Users(uName,uPass,uSex,uAge,uPhone) values(?,?,?,?,?)";
                PreparedStatement stmt = conn.prepareStatement(sql);
                stmt.setString(1,name);
                stmt.setString(2,password);
                stmt.setString(3,sex1);
                stmt.setString(4,age);
                stmt.setString(5,phone);

                stmt.execute();

            }
        }


        /**
         * @param min
         *            最小值
         * @param max
         *            最大值
         * @return
         * @describe 生成一个指定范围内的整数,[min, max]之间的随机整数
         */
        public static int getRandom(int min, int max) {
            int randomNum = 0;
            try {
                Thread.sleep(1000);
                Random rand = new Random();
                // 获取随机的数，范围值包小包大
                randomNum = rand.nextInt(max) % (max - min + 1) + min;
                System.out.println("随机数：" + randomNum);
            } catch (InterruptedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
                System.out.println("获取随机整数，发生异常：" + e.getMessage());
            }
            return randomNum;
        }


}
