const pokemonList = document.getElementById("pokemon-list");
const loadMoreButton = document.getElementById("load-more-button");

const maxRecords = 151;
const limit = 10;
let offset = 0;

function loadPokemonItems(offset, limit) {
    pokeApi.getPokemons(offset, limit)
            .then((pokemons = []) => {
                pokemonList.innerHTML += pokemons.map(pokemon => `
                <li class="pokemon ${pokemon.type}">
                    <div class="container">
                        <span class="number">#${pokemon.number}</span>
                        <span class="name">${pokemon.name}</span>
                    </div>
                    <div class="detail">
                        <ol class="types">
                            ${pokemon.types.map(type => `<li class="type ${type}">${type}</li>`).join('')}
                        </ol>
                        <img src="${pokemon.photo}" alt="${pokemon.name}"> 
                    </div>                
                </li>
            `).join(''); 
            });
}

loadPokemonItems(offset, limit);

loadMoreButton.addEventListener("click", () => {
    offset += limit;
    const qtdRecordNewPage = offset + limit;

    if(qtdRecordNewPage >= maxRecords) {
        const newLimit = maxRecords - offset;   
        loadPokemonItems(offset, newLimit);

        loadMoreButton.parentElement.removeChild(loadMoreButton);
    } else {
        loadPokemonItems(offset, limit);
    }   
})
