function fibo(rank) {
  tmp=1
  res=1
  for (i=3; i<=rank; i++) {
    res+=tmp    // reçoit la somme
    tmp=res     // reçoit la somme moins
      -tmp      // la valeur précédente.
  }
  return // retourne 
   res   // le résultat
}


function testFibo(max) {
  for(i=1; i<=max; ++i) {
    tmp=fibo(i)
    console.log("fibo("+i+")="+tmp)
  }
}

testFibo(10, 15)
