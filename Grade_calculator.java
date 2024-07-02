import java.util.*;

class students{
    int total,sum,avg,subject;
    int marks[];
    char garde;
    Scanner sc=new Scanner(System.in);

   //function for taking input from user
   void input(){
        System.out.println("Enter no.of subjects:");
        subject=sc.nextInt();
        marks=new int[subject];
        for (int i=0;i<subject;i++){
        System.out.println("Enter marks of subject "+(i+1)+":");
        marks[i]=sc.nextInt();
    }
}   
    //function for calculating total and percenatage
    void average(){
        sum=0;
        for(int i=0;i<subject;i++){
            sum+=marks[i];
        }
        avg=sum/subject;
    }
    //function for calculating garde
    void grade(){
        if (avg>=75){
            System.out.println("Grade A");
        }
        else if (avg>=60 && avg<=75){
            System.out.println("Grade B");
        }
        else if (avg>=50 && avg<=60){
            System.out.println("Grade C");
        }
        else if (avg>=35 && avg<=50){
            System.out.println("Grade D");
        }
        else{
            System.out.println("FAIL ");
        }

    }

    //function for displaying the result
    void display(){
        System.out.println("___RESULT___");
        for (int i=0;i<subject;i++){
        System.out.println("Marks of subject "+(i+1)+":"+marks[i]);
    }
        System.out.println("Total Marks:"+ sum);
        System.out.println("Average Percentage:"+avg);
        grade();
    }
}

 class Grade_calculator {
    public static void main(String []args){
        students s1=new students();
        s1.input();
        s1.average();
        s1.display();
    }
    
}
