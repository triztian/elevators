import React from 'react'
import ControlPanel from "./ControlPanel";

export default function Floor(props) {
	return (
		<div className="Floor">
			<ControlPanel 
				floors={props.floors}
				floorNumber={props.floorNumber}
			/>
			<div></div>
			<div></div>
			<div></div>
		</div>
	)
}