import React from 'react';

export default function CallButton(props) {

	const onClick = (evt) => {
		props.onCallElevator(evt.target.value);
	}

	return (
		<div className="CallButton">
			<button 
				type="button" 
				value={props.floorNumber}
				onClick={onClick}
			>Call</button>
		</div>
	)
}