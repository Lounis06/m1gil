function setStyle(elt, value) {
  if (value) {
    elt.style = "none";
  } else {
    elt.className = "error";
  }
  
  return value;
}

function testName(elt) {
  return setStyle(elt, elt.innerHTML.length >= 3);
}

function testFormat() {
  var elt = document.getElementById("format");
  var input = elt.childNodes;
  
  for (var k = 0; k < input.length; ++k) {
    if (!input[k].disabled) {
      return setStyle(elt, true);
    }
  }
  
  return setStyle(elt, false);
}

function testEmail() {
  var elt = document.getElementById("email");
  var mail = elt.innerHTML;
  var pattern = /[a-z]+@[a-z]+\.[a-z]+/i;
  
  return setStyle(elt, pattern.test(mail));
}

function testAll() {
  var prop = true;
  prop = prop && testName(document.getElementById("lastname"));
  prop = prop && testName(document.getElementById("firstname"));
  prop = prop && testFormat();
  prop = prop && testEmail();
  
  if (!prop) {
    
  } else {
    
  }
}
