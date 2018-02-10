const App = ({cars = []}) =>
    <div>
        <ul>
            {cars.map((car, i) =>
                <li key={i}>{car.adName} : {car.price}</li>
            )}
        </ul>
    </div>

export default App
