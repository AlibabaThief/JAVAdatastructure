package arrayStack;

public class Caculator {
    public static void main(String[] args) {
        //根据前面的思路 完成表达式的运算
        String experssion = "3+2*6-2";
        Stack1 ns1 = new Stack1(10);
        Stack1 ops1 = new Stack1(10);
        int index = 0;//用于扫描
        int num1 = 0;
        int num2 = 0;
        int oper = 0;
        int res = 0;
        char ch = ' ';//将每次扫描得到的char保存到ch
        String keepnum = "";//用于拼接多位数字
        while (true) {
            //依次得到experssion 的每一个字符
            ch = experssion.substring(index, index + 1).charAt(0);//
            //判断ch是什么 然后做相应的处理
            if (ops1.isOper(ch)) {//如果是运算符 判断当前的盏是否为空
                if (!ops1.isEmpty()) {
                    //如果符号栈有操作副    比较
                    if (ops1.priority(ch) <= ops1.priority(ops1.peek())) {
                        num1 = ns1.pop();
                        num2 = ns1.pop();
                        oper = ops1.pop();
                        res = ns1.cal(num1, num2, oper);
                        ns1.push(res);
                        ops1.push(ch);
                    } else {
                        //如果当前的操作服的优先级 大于栈中的操作符
                        ops1.push(ch);
                    }
                } else {
                    //如果为空直接入符栈
                    ops1.push(ch);
                }

            } else {//如果是 数 则直接入数栈
                ns1.push(ch - 48);//1+3  "这里扫描的字符 1 不是数字 1

                //处理多位数时 不能发现是一个数 就立即入展  因为他可能是多位数
                //处理 数  需要向exppression 的表达式的index 后再看以为  如果是 数进行 扫描 如果是符号菜入展
                //因此 需要 定义一个变量字符串  用于拼接
                keepnum += ch;
                //判断下一个字符 是不是数字 如果是数字 继续扫描  如果是运算符 则入展
                //如果ch已经是expersission的最后一位 则直接入展
                if (index == experssion.length() - 1) {
                    ns1.push(Integer.parseInt(keepnum));
                } else {

                    if (ops1.isOper(experssion.substring(index + 1, index + 2).charAt(0))) {
                        ns1.push(Integer.parseInt(keepnum));
                        //So importace !!!!!清空
                        keepnum = " ";

                    }
                }
                //让index+1 并判断是否  扫描到   experssion
                index++;
                if (index >= experssion.length()) {
                    break;
                }
            }
            //
            while (true) {
                //如果符号栈 为 空 则 计算结束   计算到了最后的结果
                if (ops1.isEmpty()) {
                    break;
                }
                num1 = ns1.pop();
                num2 = ns1.pop();
                oper = ops1.pop();
                res = ns1.cal(num1, num2, oper);
                ns1.push(res);
            }
            int res2 = ns1.pop();
            System.out.printf("表达式%s =%d", experssion, res2);
        }
    }
}
class Stack1 {
    private int top = -1;
    private int[] arr;
    private int maxsize;

    public Stack1(int maxsize) {

        this.maxsize = maxsize;
        arr = new int[maxsize];
        this.arr = arr;
        this.top = top;


    }

    public boolean isEmpty() {

        return top == -1;

    }

    //返回当前的栈顶   不是真正的pop
    public int peek() {
        return arr[top];
    }

    public boolean isFull() {

        return top == maxsize - 1;
    }

    public void push(int n) {
        if (isFull()) {
            System.out.println("stack is full");
            return;
        }
        top++;
        arr[top] = n;

    }

    public int pop() {
        if (isEmpty()) {
            System.out.println("stack is empty");//throw  new RuntimeException("stack is empty")
        }
        int value = arr[top];
        top--;
        return value;
    }

    //遍历时需要从站定开始显示数据
    public void list() {
        if (isEmpty()) {
            System.out.println("can't show data because no data");
        }
        for (int i = top; i >= 0; i--) {
            System.out.println(arr[i]);
        }
    }

    //返回运算符的优先级 优先级 是程序员来确定的，优先级 使用数字表示  数字越大  优先级 越高
    public int priority(int oper) {

        if (oper == '*' || oper == '/') {
            return 1;
        } else if (oper == '+' || oper == '-') {
            return 0;
        } else {
            return -1;//假定目前的表达式 只有+-*/
        }
    }

    //判断是否为运算符
    public boolean isOper(char val) {
        return val == '+' || val == '-' || val == '*' || val == '/';
    }

    //计算方法  char 和int混用
    public int cal(int num1, int num2, int Oper) {
        int res = 0;
        switch (Oper) {
            case '+':
                res = num1 + num2;
            case '-':
                res = num2 - num1;
                break;
            case '*':
                res = num1 * num2;
            case '/':
                res = num2 / num1;
                break;
            default:
                break;
        }
        return res;
    }


}