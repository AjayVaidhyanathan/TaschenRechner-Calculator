### Version_0.1.0

## Layout Structure:


#### Layout files
- activity_main.xml
 * button_layout.xml
 * Strings.xml
 * colors.xml

- java
 * MainActivity.java

 ---

 ## Architecture:

 Layouts:

1. The button_layout.xml file is attached to activity_main.xml with a relative_layout as contrast compared to parent which is a Linear_Layoutb

activity_main.xml:


     <include
        layout="@layout/button_layout"
        android:layout_width="match_parent"
        android:layout_height="match_parent"/>

2. A relative Grid layout is choosed instead of a complex linear layout to easy assign buttons

java:

1. Each button is assigned independent value and it is initialized in similar way for all buttons

       btn0 = findViewbyId(R.id.btn0);

2. To get values from these buttons individual function needs to be called by

       btn0.setOnClickListener(new View.OnClickListener() {
          @Override
           public void onClick(View v) {
           txtInput.setText(txtInput.getText() + "0");
           txtOutput.setText(null);
          }
       });     
  what id does is onClicking btn0 which is identified by previous initialization and gets text for function on the same time printing on txtInput textview screen. similar method is used to call all buttons as well as functions

3. Now this function changes for operator since we need to take values from button to process arithmetic values. it can be done as

       btAdd.setOnClickListener(new View.OnClickListener() {
          @Override
          if (txtInput.getText().length() != 0) {
                    m1 = Float.parseFloat((txtInput.getText() + ""));
                    fAdd = true;
                    decimal = false;
                    txtInput.setText(null);
         }
       });    
  Complex? No lets decode
 we cannot use operator before number hence
       txtInput.getText(.length()! = 0)

 When input value is not present this statement doesnt get triggered. After entering the if loop we need to capture the number for that,
       m1 = Float.parseFloat((txtInput.getText() + ""));
       fadd = true;

  Now we use boolean to identify values for cases which we are going to be used earlier. The additional use of the above line is that on continous clicking of any operator other than a number instantly closes the app, hence it prevents also from crashing . This logic is used for all operator.        

4. m1 and m2 values are assigned to store value before operator and after operator. hence btnEqual function can be simplified by simple if or switch case statement. In this program we used only if statements to avoid confusion.
