import React, {useState} from 'react'

export default function FloorSelector(props) {

	const [selectedFloor, setSelectedFloor] = useState(props.selectedFloor);

	const onChange = e => {
		setSelectedFloor(e.target.value);
		props.onSelectFloor(e.target.value);
	}

	let floorOptions = props.floors.map(f => {
		return (<option key={`fo${f.number}`} value={f.number}>{f.name}</option>);
	});

	return (
		<div className="FloorSelector">
			<select onChange={onChange} value={selectedFloor}>
				{floorOptions}
			</select>
		</div>
	);
}