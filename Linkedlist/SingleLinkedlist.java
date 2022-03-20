package Linkedlist;

public class SingleLinkedlist {

    public static void main(String[] args) {
        HeroNode h1= new HeroNode(1,"zhangfei","黑旋风");
        HeroNode h2 = new HeroNode(2,"songjiang ","jishiyu");
        HeroNode h3 = new HeroNode(3,"wuyong","zhiduoxing");
        HeroNode h4 =new HeroNode(4,"linchong","baozi");
        singleLinkList s1 =new singleLinkList();
        s1.add(h1);
        s1.add(h2);
        s1.add(h3);
        s1.add(h4);
        s1.list();
    }
}
//定义heronode  每个对象就是一个节点

class HeroNode{


    public int node;
    public String name;
    public String nickname;
    public HeroNode next;
    public HeroNode(int hnode,String hname,String hNinckname){
        this.node=hnode;
        this.name=hname;
        this.nickname=hNinckname;
    }
    //为了显示方便 重写tostring
    @Override
    public String toString(){
        return node+name+nickname;
    }
}
class singleLinkList{

    //先初始化一个头节点
    private HeroNode head = new HeroNode(0,"","");//头节点 什么都不存放
    //添加节点 到单项链表
    //思路  当不考虑编号顺序是   1找到当前链表的最后节点    2当最后节点的next指向新的节点
    public void add(HeroNode heroNode){
        //因为head 头节点 不能东  所以需要一个辅助遍历 temp
        HeroNode temp = head;
        //遍历链表 找到 最后
        while(true){
            //当temp.next==NULL
            if(temp.next==null){
                break;
            }
            temp=temp.next;//没有找到 将 temp后移
        }
        //当退出while循环时  temp指向了链表的最后
        temp.next=heroNode;

    }
        //按照编号添加
    public void addbyorder(HeroNode heroNode){
        //因为头节点不能动 因此 仍然通过辅助指针 来 帮助找到添加的位置
        //因为单链表,So temp 位于 添加位置的前一个节点,否则插入不进去
        HeroNode temp =head;
        boolean flag =false;//标识 添加的编号是否存在 ， 默认为false
        while(true){
            if(temp.next==null){
                break;
            }
            if(temp.next.node > heroNode.node){
                //位置找到， 就在 temp 的后面插入
                break;
            }else if(temp.next.node== heroNode.node){
                //说明希望添加的heroNode的编号已经存在   不能添加
                flag=true;//说明编号存在
                break;

            }
            temp = temp.next;//后移 等于遍历的操作
        }//判断flag的值
        if(flag){
            //说明编号存在
            System.out.println("待插入的英雄已经在链表中存在");
        }else{
            heroNode.next=temp.next;
            temp.next=heroNode;
        }
    }
// 完成修改节点的信息， 根据 编号来修改 即node 不能修改
    //根据  newheronode 的node 来修改
    public void update(HeroNode newheronode){

        if(head.next==null){
            System.out.println("链表为空");
            return ;
        }
        //找到需要修改的节点   根据node的编号
        HeroNode temp =head.next;
        boolean flag =false;//表示是否找到节点
        while(true){
            if(temp==null){
                break;//表示链表遍历结束
            }
            if (temp.node==newheronode.node){
                flag=true;
                break;
            }
            temp=temp.next;
        }
        //根据flag判断是否找到 了要修改的节点
        if(flag==true){
            temp.name=newheronode.name;
            temp.nickname=newheronode.nickname;
        }else{
            System.out.printf("Nothing find node不能修改 %d",newheronode);
        }

    }







    //显示 链表的方法
    public void list(){
    //通过辅助变量 来遍历整个  链表  why  need  辅助变量？  因为head 不能动
        if(head.next==null){
            System.out.println("linkedlist is empty");
            return ;
        }
        //因为头解节点不能动  so  need  辅助变量来遍历
        HeroNode temp = head.next;
        while(true){
            if(temp==null){
                break;
            }
            //输出节点的 information
            System.out.println(temp);
            //将next后移
            temp=temp.next;
        }

    }


}

