

Program to find uncoupled integer in an Array

[1,1,3] 3 uncoupled integer

1. Using Set data structure

public int finduncoupledinteger( int[] a){

Set<Integer> viewed= new Hashset<Integer>();

for( Integer i : a){

if(viewed.contains(i)){
	viewed.remove(i);
}
  else
  {	  viewed.add(i);
	

  }
}

for(Integer uncoupledint : viewed){
	
	return uncoupledint;
	
}

throw new exception IllegalArgumentException("Does not contain uncoupled integer");
}

