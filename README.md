<p align="center"><a href="https://github.com/GagiuFilip1/PESNA-INFOEDUCATIE"><img src="https://github.com/GagiuFilip1/PESNA-INFOEDUCATIE/blob/master/PesnaSite/public/img/logoPesna.png" height="100" /></a></p>
<p align="center">
<a href="https://travis-ci.org/teodorciripescu/PESNA-INFOEDUCATIE"><img src="https://api.travis-ci.org/teodorciripescu/PESNA-INFOEDUCATIE.svg?branch=master" alt="build status"></a>
<img src="https://img.shields.io/badge/platforms-windows%2Fmacos%2Flinux-brightgreen.svg" alt="platforms">
<a href="https://github.com/GagiuFilip1/PESNA-INFOEDUCATIE/issues"><img src="https://img.shields.io/github/issues/GagiuFilip1/PESNA-INFOEDUCATIE.svg" alt="GitHub issues"></a>
</p>


-------------------------------------------------------------------------------------------------------------------------------------


About
-----
  PESNA (Project Especially Shaped for National Awareness) este un proiect ce isi propune sa readuca valorile folclorului in era contemporana. Acesta are la baza un game engine ce construieste cadrul nivelelor.
  Hartile sunt create prin intermediul editorului integrat,mapele salvate, putand fi urcate pe site-ul proiectului pentru a fi jucate si de alti utilizatori.
  Nivelul construit este jucat printr-un 2d platformer, in care personajul trebuie sa treaca de diferite stagii pentru a descoperi povestea nivelului, iar in cele din urma fiind nevoit sa lupte cu rivalul acestuia.Editorul permite selectarea backgroundului, platformei, mobilor, rivalului principal si crearea povestii.Proiectul isi propune sa faciliteze lucrul in echipa, hartile create in editor putand fi realizate in echipa. Doritorii, facandu-si un cont, pot crea grupuri temporale in care sa lucreze impreuna, comunicare fiind intermediata de chatul integrat in editor. Odata ce unul dintre participanti face o schimbare asupra hartii aflate in creare, toti ceilalti participanti vor vedea demersurile in timp real.
  Aplicatia vine incarcata deja cu o mapa ce reda basmul lui Harap Alb,ce poate fi gasit oricand pe site.

Demo
----

Tehnologii
----------
- Proiectul este realizat in [LibGDX], Java. [LibGDX] a fost folosit ca metoda de randare, toate controalele, Game Physics-ul, AI-urile cat si logica de randare au fost facute integral de la 0.
- biblioteca opensource realizata de Trixt0r folosita pentru integrarea unui animation-manager folosit la randarea animatiilor bone-based din [Spriter 2D].
- [Node.js] folosit pentru server cat si pentru site.
- [SOCKET.IO] pentru real-time connection.
- sistem de Chat bazat pe protocolul [XMPP]([Openfire] si [Smack]).
- Azure pentru hostarea serverului.

<p align="center"><a href="https://github.com/libgdx/libgdx"><img src="https://camo.githubusercontent.com/f70f169cab179f13f70e892b3251e6f2032be66d/687474703a2f2f6c69626764782e6261646c6f67696367616d65732e636f6d2f696d672f6c6f676f2e706e67" height="50" hspace="20" /></a>
<a href="https://github.com/Trixt0r/spriter"><img src="https://avatars2.githubusercontent.com/u/1701058?v=3&s=400" height="50" /></a>
<img src="https://camo.githubusercontent.com/9c24355bb3afbff914503b663ade7beb341079fa/68747470733a2f2f6e6f64656a732e6f72672f7374617469632f696d616765732f6c6f676f2d6c696768742e737667" height="50" hspace="20" /></a>
<a href="https://github.com/socketio/socket.io"><img src="https://www.pubnub.com/wp-content/uploads/2014/07/SOCKETIOICON.gif" height="50" hspace="10" /></a>
<a href="https://github.com/igniterealtime/Openfire"><img src="https://www.unixmen.com/wp-content/uploads/2015/01/openfire.png" height="50" hspace="20" /></a>
<a href="https://github.com/igniterealtime/Smack"><img src="http://blogs.fsfe.org/vanitasvitae/files/2017/06/smack-logo-with-text.png" height="70" hspace="20" /></a>
</p>
 
[LibGDX]: https://github.com/libgdx/libgdx
[Spriter 2D]: https://github.com/Trixt0r/spriter
[Node.js]: https://github.com/nodejs/node
[SOCKET.IO]: https://github.com/socketio/socket.io
[Openfire]: https://github.com/igniterealtime/Openfire
[Smack]: https://github.com/igniterealtime/Smack
[XMPP]: https://xmpp.org/
