import React from 'react'

import ControlPanel from "./ControlPanel";

import './css/Floor.css'

export default function Floor(props) {

	return (
		<div className={props.debug ? 'Floor debug': 'Floor'}>
			<div className={props.debug ? 'Floor-Name debug' : "Floor-Name"}>
				<h4>{props.floorName}</h4>
			</div>
			<ControlPanel 
				debug={props.debug}
				floors={props.floors}
				floorNumber={props.floorNumber}/>
		</div>
	)
}