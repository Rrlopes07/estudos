using DesafioPOO.Models;

Nokia nokia = new Nokia("1198562415", "X211", "1862185612", 16);

nokia.Ligar();
nokia.ReceberLigacao();

Iphone iphone = new Iphone("452136354878", "I13", "8652135486", 64);

iphone.Ligar();
iphone.ReceberLigacao();

nokia.InstalarAplicativo("WhatsApp");
iphone.InstalarAplicativo("Instagram");
