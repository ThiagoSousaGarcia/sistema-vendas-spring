# 🔄 Modernização do Fluxo de Contratação e Manutenção – CG Investimentos

---

## 1. 🎯 Por que mudamos?

Antes, os fluxos de **contratação** e **manutenção de produtos** utilizavam duas aplicações Java: o **Orquestrador**, responsável por controlar uma máquina de estados com toda a lógica do processo, e o **ACL Integrações**, que cuidava das integrações externas.

Apesar de funcional, essa arquitetura apresentava diversos desafios práticos, especialmente para quem precisava acompanhar, manter ou evoluir o sistema:

- **Análise de erros complexa**: como o fluxo era executado dentro de um sistema grande e interligado, entender onde e por que algo falhou exigia conhecimento profundo do código e tempo para investigação.
- **Pouca visibilidade para outros times**: não era simples visualizar em que etapa o processo estava, o que dificultava o suporte e o acompanhamento por produto ou operações.
- **Alto acoplamento entre as etapas**: os componentes do sistema eram fortemente conectados, o que tornava a manutenção mais arriscada e custosa.
- **Baixa flexibilidade para evoluções**: ajustes no fluxo exigiam deploys complexos, testes pesados e mexiam em blocos de código com múltiplas responsabilidades.

Essas limitações prejudicavam tanto o acompanhamento do dia a dia quanto a capacidade de adaptar o sistema às mudanças de negócio de forma rápida e segura.

---

## 2. 🧠 O que foi feito?

Para resolver esses pontos, migramos os fluxos de **contratação** e **manutenção** para uma arquitetura moderna e desacoplada, baseada em componentes serverless da **AWS**.

A solução atual é composta por:

- **AWS Step Functions**: responsável por orquestrar cada etapa do processo de forma visual, transparente e controlável.
- **AWS Lambda**: cada passo do processo é executado por uma função isolada, com responsabilidade única, facilitando manutenção e testes.
- **SNS (Simple Notification Service)**: envia notificações automáticas em caso de falha para facilitar a criação de incidentes e o monitoramento.
- **Mecanismo de fallback e compensação via Catch**: ao detectar uma falha em qualquer etapa, o Step Function redireciona automaticamente para blocos específicos de tratamento de erro, que podem desfazer ações anteriores ou acionar alertas conforme o tipo da falha.

Esse modelo torna o processo mais transparente e resiliente, com capacidade de reação rápida a erros e visibilidade total para todos os envolvidos.

---

## 3. 🚀 Quais os benefícios?

### 🔍 Rastreabilidade e Visibilidade
- Acompanhamento em tempo real de cada execução, com histórico completo das etapas realizadas.
- Facilidade para identificar rapidamente o ponto de falha sem depender de logs internos de sistemas complexos.
- Visualização clara e acessível do fluxo por qualquer time (negócio, produto, suporte, etc).

### 🧩 Separação de responsabilidades
- Cada função Lambda tem uma responsabilidade clara (ex: validar dados, consultar serviço externo, persistir informação).
- Isso facilita alterações pontuais sem impactar o restante do fluxo e reduz o risco de regressões.

### 🔄 Tratamento inteligente de falhas
- O Step Function utiliza o bloco `Catch` para capturar erros e redirecionar o fluxo para uma etapa de **compensação** (rollback) ou **tratamento específico**.
- Isso evita que falhas propaguem de forma descontrolada e permite desfazer operações com consistência.

### ⚙️ Facilidade de evolução e manutenção
- Adição ou modificação de etapas pode ser feita de forma isolada.
- Como as funções são independentes e reutilizáveis, novas necessidades de negócio podem ser atendidas com rapidez e segurança.

---

## 4. 🛠️ O que foi alterado?

> *(Espaço para incluir prints e detalhes técnicos do fluxo)*

Apesar de já existir uma estrutura inicial do Step Function, ela estava incompleta e não contemplava todas as regras e etapas da lógica real de negócio. Para torná-la funcional e robusta, foi necessário:

- Implementar **diversas novas funções Lambda**, para cobrir etapas da contratação e manutenção que ainda não estavam mapeadas.
- Configurar blocos `Catch` em pontos estratégicos do fluxo para controlar falhas e realizar compensações quando necessário.
- Integrar com **SNS** para envio de notificações automáticas de erro, alimentando o sistema de incidentes.
- Revisar e organizar a sequência do fluxo no Step Function para refletir corretamente as regras e dependências de negócio.
- Melhorar a **documentação interna**, com logs estruturados e nomes descritivos nos passos do fluxo para facilitar o entendimento.

> **Exemplos de materiais a incluir:**
> - `print-step-function-fluxo-completo.png`  
> - `print-falha-com-catch-e-rollback.png`  
> - `print-sns-alerta-erro.png`

---

### ✅ Conclusão

Com essa modernização, os fluxos de **contratação e manutenção de produtos** do CG Investimentos se tornaram mais:

- **Confiáveis** – tratamento automático de falhas e reversões controladas.
- **Transparente** – rastreáveis por qualquer time
