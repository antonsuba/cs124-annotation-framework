package solution.expressions;

import solution.interfaces.Expression;

public class OrExpression implements Expression{
	private boolean exp1;
	private boolean exp2;
	
	public OrExpression(boolean exp1, boolean exp2){
		this.exp1 = exp1;
		this.exp2 = exp2;
	}
	
	@Override
	public boolean interpret() {
		// TODO Auto-generated method stub
		return exp1 || exp2;
	}

}
