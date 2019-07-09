(window.webpackJsonp=window.webpackJsonp||[]).push([[0],[,,,,,,,,,function(e,t,n){e.exports=n(23)},,,,,,function(e,t,n){},function(e,t,n){},,function(e,t,n){},function(e,t,n){},function(e,t,n){},function(e,t,n){},function(e,t,n){},function(e,t,n){"use strict";n.r(t);var o=n(0),r=n.n(o),a=n(5),c=n.n(a),l=(n(15),n(8)),u=n(1),s=(n(16),n(2)),i=n.n(s),f=n(3),m=n(6),v=n(7),b=new(function(){function e(){Object(m.a)(this,e)}return Object(v.a)(e,[{key:"listFloors",value:function(){var e=Object(f.a)(i.a.mark(function e(){return i.a.wrap(function(e){for(;;)switch(e.prev=e.next){case 0:return"http://localhost:9000/floors",e.abrupt("return",fetch("http://localhost:9000/floors",{mode:"cors"}).then(function(e){return e.json()}));case 2:case"end":return e.stop()}},e)}));return function(){return e.apply(this,arguments)}}()},{key:"getElevator",value:function(){var e=Object(f.a)(i.a.mark(function e(t){var n;return i.a.wrap(function(e){for(;;)switch(e.prev=e.next){case 0:return n="http://localhost:9000/elevator/".concat(t),e.abrupt("return",fetch(n,{mode:"cors"}).then(function(e){return e.json()}));case 2:case"end":return e.stop()}},e)}));return function(t){return e.apply(this,arguments)}}()},{key:"callElevator",value:function(){var e=Object(f.a)(i.a.mark(function e(t,n){var o;return i.a.wrap(function(e){for(;;)switch(e.prev=e.next){case 0:if(Number.isInteger(parseInt(t))){e.next=2;break}return e.abrupt("return",new Error("from argument must be a valid number"));case 2:return o="http://localhost:9000/floor/".concat(t),null!==n&&void 0!==n&&Number.isInteger(parseInt(n))&&(o+="/to/".concat(n)),e.abrupt("return",fetch(o,{method:"POST",mode:"cors"}).then(function(e){return e.json()}));case 5:case"end":return e.stop()}},e)}));return function(t,n){return e.apply(this,arguments)}}()},{key:"watchElevator",value:function(e,t){var n=e;e.startsWith("E")&&(n=e.substring(1));var o="ws://localhost:9000/elevator/".concat(n,"/updates");return new Promise(function(e,r){var a=new WebSocket(o,"*"),c=function(){a.close()};a.onopen=function(){e(c)},a.onerror=function(e){r(e)},a.onmessage=function(e){var o=JSON.parse(e.data);console.log("api.watchElevator",o),"E".concat(n)===o.id&&t(o)}})}}]),e}());n(18);function E(e){return r.a.createElement("div",{className:"CallButton"},r.a.createElement("button",{type:"button",value:e.floorNumber,onClick:function(t){e.onCallElevator(t.target.value)}},"Call"))}n(19);function d(e){var t=Object(o.useState)(e.selectedFloor),n=Object(u.a)(t,2),a=n[0],c=n[1];return r.a.createElement("div",{className:"FloorSelector"},r.a.createElement("label",{htmlFor:"FS".concat(e.floorNumber)},"Destination"),r.a.createElement("select",{id:"FS".concat(e.floorNumber),onChange:function(t){c(t.target.value),e.onSelectFloor(t.target.value)},value:a},r.a.createElement("option",{value:"-1"},"None"),e.floors.map(function(e){return r.a.createElement("option",{key:"fo".concat(e.number),value:e.number},e.name)})))}n(20);var h=-1;function p(e){var t=Object(o.useState)(null),n=Object(u.a)(t,2),a=n[0],c=n[1];return r.a.createElement("div",{className:e.debug?"ControlPanel debug":"ControlPanel"},r.a.createElement(d,{floorNumber:e.floorNumber,floors:e.floors,selectedFloor:h,onSelectFloor:c,debug:e.debug}),r.a.createElement(E,{floorNumber:e.floorNumber,onCallElevator:function(){var t=e.floorNumber,n=a;console.log("Elevator called: from: ",t,"to: ",n),b.callElevator(t,n).then(function(e){console.log("onCallElevator: success: ",e)}).catch(function(e){console.error("onCallElevator: ",e)})},debug:e.debug}))}n(21);function g(e){return r.a.createElement("div",{className:e.debug?"Floor debug":"Floor"},r.a.createElement("div",{className:e.debug?"Floor-Name debug":"Floor-Name"},r.a.createElement("h4",null,e.floorName)),r.a.createElement(p,{debug:e.debug,floors:e.floors,floorNumber:e.floorNumber}))}n(22);function N(e){var t=Object(o.useState)(0),n=Object(u.a)(t,2),a=n[0],c=n[1];Object(o.useEffect)(function(){b.getElevator(e.elevatorID).then(function(e){c(e.currentFloor.number)}).catch(function(e){console.error("getElevator: ",e)})},[e.elevatorID]),Object(o.useEffect)(function(){b.watchElevator("E"+e.elevatorID,function(e){console.log("watchElevator: ",e),c(e.currentFloor.number)}).then(function(e){console.log("watching elevator: ",e)}).catch(function(e){console.error("watchElevator: ",e)})},[e.elevatorID]);var l=e.floors.map(function(t){return a===t.number?r.a.createElement("div",{id:"EF".concat(t.number),className:e.debug?"Elevator debug":"Elevator",key:t.number},r.a.createElement("h4",null,"E",e.elevatorID),r.a.createElement("h6",null,"Floor ",a)):r.a.createElement("div",{id:"EF".concat(t.number),className:e.debug?"Elevator-Stub debug":"Elevator-Stub",key:t.number})});return r.a.createElement("div",{className:e.debug?"Elevator-Line debug":"Elevator-Line"},l)}Boolean("localhost"===window.location.hostname||"[::1]"===window.location.hostname||window.location.hostname.match(/^127(?:\.(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)){3}$/));c.a.render(r.a.createElement(function(){var e=Object(o.useState)([]),t=Object(u.a)(e,2),n=t[0],a=t[1];return Object(o.useEffect)(function(){b.listFloors().then(a).catch(function(e){console.error("listFloors: ",e)})},[]),r.a.createElement("div",{className:"App"},r.a.createElement("h1",{className:"App-Header"},"Elevator"),r.a.createElement("div",{className:"Building"},r.a.createElement("div",{className:"Floors"},n.reverse().map(function(e){return r.a.createElement(g,{key:"f".concat(e.number),floors:n,floorName:e.name,floorNumber:e.number,debug:!1})})),r.a.createElement("div",{className:"Elevators"},Object(l.a)(Array(3).keys()).map(function(e){return r.a.createElement(N,{key:"E"+e,elevatorID:e,debug:!1,floors:n})}))))},null),document.getElementById("root")),"serviceWorker"in navigator&&navigator.serviceWorker.ready.then(function(e){e.unregister()})}],[[9,1,2]]]);
//# sourceMappingURL=main.a46ef47b.chunk.js.map