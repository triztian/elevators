import React from 'react'

import ControlPanel from "./ControlPanel";

import './Floor.css'

export default function Floor(props) {

	return (
		<div className="Floor">
			<div className="Floor-Name">
				<h4>{props.floorName}</h4>
			</div>
			<ControlPanel 
				floors={props.floors}
				floorNumber={props.floorNumber}/>
		</div>
	)
}