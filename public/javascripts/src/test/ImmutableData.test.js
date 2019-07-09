import ImmutableData from '../util/ImmutableData'

it('ImmutableData should preserve values', () => {

	const srcNumbers = [1,2,3];
	const srcObj = {
		a: {
			x: 99,
			y: -123,
			z: {
				q: 'qstring'
			}
		},
		b: 'a string'
	}
	const srcDate = new Date();
	const srcHours = srcDate.getHours();

	const data = new ImmutableData(srcNumbers, srcObj, srcDate);

	// modify a numbers entry
	expect(() => {
		const resNumbers = data.numbers;
		resNumbers[2] = 99;
	}).toThrow();
	expect(srcNumbers[2]).toEqual(3);

	// modify the nested object
	expect(() => {
		const resObj = data.data;
		resObj.a.x = 300;
	}).toThrow();
	expect(srcObj.a.x).toEqual(99);

	// modify the nested object
	expect(() => {
		const resObj = data.data;
		resObj.a.z.q = 999
	}).toThrow();
	expect(srcObj.a.z.q).toEqual('qstring');

	const resDate = data.date;
	resDate.setHours(1);
	expect(srcHours).toEqual(srcDate.getHours());
})