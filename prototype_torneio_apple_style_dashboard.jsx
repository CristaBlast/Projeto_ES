export default function TournamentPrototype() {
  const frontoffice = [
    "Visualização de Árbitros",
    "Visualização de Equipas",
    "Calendário de Jogos"
  ];

  const backoffice = [
    "Gestão de Calendário",
    "Atribuição de Arbitragem",
    "Venda de Bilhetes",
    "Login Seguro",
    "Gestão de Grupos",
    "ID Digital",
    "Criação de Equipas",
    "Gestão de Estádios",
    "Campos de Treino",
    "Deslocações"
  ];

  return (
    <div className="min-h-screen bg-[#f5f5f7] text-black font-sans p-8">
      <div className="max-w-7xl mx-auto">
        <div className="flex items-center justify-between mb-12">
          <div>
            <h1 className="text-5xl font-semibold tracking-tight">
              Tournament Manager
            </h1>
            <p className="text-gray-500 mt-3 text-lg">
              Plataforma moderna para gestão de competições
            </p>
          </div>

          <button className="bg-black text-white px-6 py-3 rounded-2xl shadow-sm hover:scale-105 transition-all">
            Login
          </button>
        </div>

        <div className="grid grid-cols-1 lg:grid-cols-3 gap-6 mb-10">
          <div className="bg-white rounded-3xl p-8 shadow-sm border border-gray-100">
            <p className="text-gray-400 text-sm mb-2">Jogos Agendados</p>
            <h2 className="text-5xl font-semibold">48</h2>
          </div>

          <div className="bg-white rounded-3xl p-8 shadow-sm border border-gray-100">
            <p className="text-gray-400 text-sm mb-2">Equipas</p>
            <h2 className="text-5xl font-semibold">24</h2>
          </div>

          <div className="bg-white rounded-3xl p-8 shadow-sm border border-gray-100">
            <p className="text-gray-400 text-sm mb-2">Estádios</p>
            <h2 className="text-5xl font-semibold">12</h2>
          </div>
        </div>

        <div className="grid grid-cols-1 lg:grid-cols-2 gap-8 mb-12">
          <div className="bg-white rounded-[32px] p-8 shadow-sm border border-gray-100">
            <div className="flex items-center justify-between mb-8">
              <div>
                <h2 className="text-3xl font-semibold">Frontoffice</h2>
                <p className="text-gray-500 mt-1">
                  Funcionalidades públicas
                </p>
              </div>

              <div className="w-14 h-14 rounded-2xl bg-black text-white flex items-center justify-center text-xl font-semibold">
                F
              </div>
            </div>

            <div className="space-y-4">
              {frontoffice.map((item, index) => (
                <div
                  key={index}
                  className="bg-[#f5f5f7] rounded-2xl p-5 hover:bg-black hover:text-white transition-all cursor-pointer"
                >
                  <p className="font-medium">{item}</p>
                </div>
              ))}
            </div>
          </div>

          <div className="bg-black text-white rounded-[32px] p-8 shadow-sm">
            <div className="flex items-center justify-between mb-8">
              <div>
                <h2 className="text-3xl font-semibold">Backoffice</h2>
                <p className="text-gray-400 mt-1">
                  Gestão interna da competição
                </p>
              </div>

              <div className="w-14 h-14 rounded-2xl bg-white text-black flex items-center justify-center text-xl font-semibold">
                B
              </div>
            </div>

            <div className="grid grid-cols-1 md:grid-cols-2 gap-4">
              {backoffice.map((item, index) => (
                <div
                  key={index}
                  className="bg-white/10 backdrop-blur rounded-2xl p-5 hover:bg-white hover:text-black transition-all cursor-pointer"
                >
                  <p className="font-medium text-sm leading-relaxed">
                    {item}
                  </p>
                </div>
              ))}
            </div>
          </div>
        </div>

        <div className="bg-white rounded-[32px] p-8 shadow-sm border border-gray-100">
          <div className="flex items-center justify-between mb-8">
            <div>
              <h2 className="text-3xl font-semibold">
                Próximos Jogos
              </h2>
              <p className="text-gray-500 mt-1">
                Calendário oficial da competição
              </p>
            </div>

            <button className="bg-black text-white px-5 py-3 rounded-2xl hover:scale-105 transition-all">
              Ver Tudo
            </button>
          </div>

          <div className="space-y-4">
            {[1, 2, 3].map((game) => (
              <div
                key={game}
                className="flex items-center justify-between bg-[#f5f5f7] rounded-2xl p-5 hover:bg-gray-200 transition-all"
              >
                <div>
                  <h3 className="font-semibold text-lg">
                    Equipa A vs Equipa B
                  </h3>
                  <p className="text-gray-500 text-sm">
                    Estádio Nacional • 21:00
                  </p>
                </div>

                <div className="text-right">
                  <p className="font-medium">12 Junho</p>
                  <p className="text-gray-500 text-sm">Grupo A</p>
                </div>
              </div>
            ))}
          </div>
        </div>
      </div>
    </div>
  );
}
