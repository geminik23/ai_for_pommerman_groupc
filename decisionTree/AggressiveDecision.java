package groupC.decisionTree;


public class AggressiveDecision extends BaseDecision {

    AggressiveDecision(DecisionTreeNode truenode, DecisionTreeNode falsenode){
       super(null, falsenode);
    }

   @Override
   protected DecisionTreeNode getBranch(DecisionParams param) {
       //TODO

       return trueNode;
   }


}
