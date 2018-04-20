/** ---- Exercice 2 ---- **/
/** Question 2.2 **/
function fibo(rank) {
  var tmp = 1;
  var res = 1;
  for (var i = 3; i <= rank; ++i) {
    res += tmp;   // reçoit la somme
    tmp = res - tmp;    // reçoit la somme moins la valeur précédente.
  }
  return res; // retourne le résultat
}


function testFibo(max) {
  for (var i = 1; i <= max; ++i) {
    var tmp = fibo(i);
    console.log("fibo(" + i + ")=" + tmp);
  }
}

testFibo(10);


/** Question 2.3 **/
tab = ['ichi', 'ni', 'san ', 'shi ', 'go', 'roku', 'shichi', 'hachi', 'kyû', 'jû'];

function print_tab() {
  for (var i = 0; i < tab.length; ++i) {
    console.log(tab[i]);
  }
}

print_tab();

/** Question 2.4 **/
function print_tab2() {
  for (var i in tab) {
    //!\ La clé i, est de type string...
    console.log(typeof i);
    console.log("" + (parseInt(i) + 1) + " : " + tab[i]);
  }
}

print_tab2();