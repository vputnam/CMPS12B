public class ListTest{

   public static void main(String[] args){

      List<Integer> I = new List<Integer>();
      List<String> S =  new List<String>();

      S.add(1,"one");
      S.add(2,"two");
      S.add(3,"three");
      S.add(4,"four");
      S.add(5,"five");
      S.add(6,"six");
      S.add(7,"seven");
      I.add(1,10);
      I.add(2,20);
      I.add(3,30);
      I.add(4,40);
      I.add(5,50);
      I.add(6,60);
      I.add(7,70);
      System.out.println(I);
      System.out.println(S);

      S.remove(4);
        System.out.println(S);
      S.remove(1);

        System.out.println(S);
      I.remove(1);
      I.remove(3);
      System.out.println(I);

      System.out.println(S.isEmpty());
      System.out.println(I.size());
      S.removeAll();
      System.out.println(S.isEmpty());
      System.out.println(I);
      System.out.println(S);
  
      System.out.println(S.equals(I));
}
}
