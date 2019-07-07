import React from 'react';

import CallButton from './CallButton'
import FloorSelector from './FloorSelector'

export default function ControlPanel(props) {
	return (
		<div className="ControlPanel">
			<FloorSelector 
				floors={props.floors}

				/> 
			<CallButton />
		</div>
	)
}
