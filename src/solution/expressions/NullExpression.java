package solution.expressions;

import solution.interfaces.Expression;

public class NullExpression implements Expression{
	private Object obj;
	
	public NullExpression(Object obj){
		this.obj = obj;
	}
	
	@Override
	public boolean interpret() {
		// TODO Auto-generated method stub
		return obj == null;
	}

}
