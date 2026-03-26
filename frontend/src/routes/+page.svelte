<script lang="ts">
	type LoanForm = {
		personalCode: string;
		amount: number;
		period: number;
	};

	type LoanDecision = {
		decision: boolean;
		loanAmount: number;
	};

	const loanDecisionEndpoint = 'http://localhost:8080/api/loans';

	let form = $state<LoanForm>({
		personalCode: '',
		amount: 4000,
		period: 24
	});
	let decision = $state<LoanDecision | null>(null);
	let errorMessage = $state('');
	let isSubmitting = $state(false);

	function resetFeedback() {
		decision = null;
		errorMessage = '';
	}

	function buildRequestPayload() {
		return {
			personalCode: Number(form.personalCode),
			amount: Number(form.amount),
			period: Number(form.period)
		};
	}

	function resolveErrorMessage(payload: unknown, fallbackMessage: string) {
		if (payload && typeof payload === 'object' && 'message' in payload) {
			const message = payload.message;
			if (typeof message === 'string' && message.trim().length > 0) {
				return message;
			}
		}

		return fallbackMessage;
	}

	async function submitApplication() {
		resetFeedback();
		isSubmitting = true;

		try {
			const response = await fetch(loanDecisionEndpoint, {
				method: 'POST',
				headers: {
					'Content-Type': 'application/json'
				},
				body: JSON.stringify(buildRequestPayload())
			});

			const payload = await response.json().catch(() => null);

			if (!response.ok) {
				errorMessage = resolveErrorMessage(payload, 'The loan request could not be processed.');
				return;
			}

			decision = payload as LoanDecision;
		} catch {
			errorMessage = 'Could not reach http://localhost:8080.';
		} finally {
			isSubmitting = false;
		}
	}
</script>

<svelte:head>
	<title>Loan decision engine</title>
</svelte:head>

<div class="min-h-screen bg-slate-50 text-slate-900">
	<div class="mx-auto max-w-4xl px-6 py-12 lg:px-8">
		<div class="space-y-8">
			<header>
				<h1 class="text-3xl font-semibold tracking-tight text-(--accent) sm:text-4xl">
					Loan decision engine
				</h1>
			</header>

			<section class="rounded-2xl border border-slate-200 bg-white p-6 shadow-sm sm:p-8">
				<div class="space-y-8">
					<form
						class="space-y-5"
						onsubmit={(event) => {
							event.preventDefault();
							submitApplication();
						}}
					>
						<div class="space-y-2">
							<div class="flex items-center gap-2">
								<label class="text-sm font-medium text-slate-700" for="personalCode">
									Personal code
								</label>
							</div>
							<input
								id="personalCode"
								name="personalCode"
								type="text"
								inputmode="numeric"
								bind:value={form.personalCode}
								class="block w-full rounded-xl border-slate-200 bg-white px-4 py-3 text-base text-slate-900 shadow-none focus:border-(--accent) focus:ring-(--accent)"
								placeholder="49002010976"
								maxlength="11"
								minlength="11"
								required
							/>
						</div>

						<div class="grid gap-5 sm:grid-cols-2">
							<div class="space-y-2">
								<label class="text-sm font-medium text-slate-700" for="amount">Loan amount</label>
								<input
									id="amount"
									name="amount"
									type="number"
									min="2000"
									max="10000"
									step="1"
									bind:value={form.amount}
									class="block w-full rounded-xl border-slate-200 bg-white px-4 py-3 text-base text-slate-900 shadow-none focus:border-(--accent) focus:ring-(--accent)"
									required
								/>
							</div>

							<div class="space-y-2">
								<label class="text-sm font-medium text-slate-700" for="period">Loan period</label>
								<input
									id="period"
									name="period"
									type="number"
									min="12"
									max="60"
									step="1"
									bind:value={form.period}
									class="block w-full rounded-xl border-slate-200 bg-white px-4 py-3 text-base text-slate-900 shadow-none focus:border-(--accent) focus:ring-(--accent)"
									required
								/>
							</div>
						</div>

						<div class="flex flex-col gap-3 sm:flex-row sm:items-center">
							<button
								type="submit"
								class="inline-flex min-h-11 items-center justify-center rounded-xl bg-(--accent) px-5 py-3 text-sm font-semibold text-white disabled:cursor-not-allowed disabled:opacity-70"
								disabled={isSubmitting}
							>
								{'Check result'}
							</button>
						</div>
					</form>

					{#if errorMessage}
						<div class="rounded-xl border bg-red-50 px-4 py-4 text-sm text-red-700">
							{errorMessage}
						</div>
					{/if}

					{#if decision}
						<div class="rounded-xl border border-slate-200 bg-slate-50 p-5">
							<div class="space-y-4">
								<div class="grid gap-4 sm:grid-cols-2">
									<div class="">
										<p class="text-sm text-slate-500">Decision</p>
										<p class="mt-1 text-lg font-semibold text-slate-950">
											{decision.decision ? 'Approved' : 'Rejected'}
										</p>
									</div>
									<div class="">
										<p class="text-sm text-slate-500">Maximum amount</p>
										<p class="mt-1 text-lg font-semibold text-(--accent)">
											{decision.loanAmount + '€'}
										</p>
									</div>
								</div>
							</div>
						</div>
					{/if}
				</div>
			</section>

			<!-- For easier debugging. ;) -->
			<div class="space-y-2 text-center">
				<h2 class="text-xl font-semibold text-slate-950">Mock Personal Codes</h2>
				<div>
					<span class="font-medium text-slate-900">Debt</span>
					<span class="ml-2 text-slate-500">49002010965</span>
				</div>
				<div>
					<span class="font-medium text-slate-900">Segment 1</span>
					<span class="ml-2 text-slate-500">49002010976</span>
				</div>
				<div>
					<span class="font-medium text-slate-900">Segment 2</span>
					<span class="ml-2 text-slate-500">49002010987</span>
				</div>
				<div>
					<span class="font-medium text-slate-900">Segment 3</span>
					<span class="ml-2 text-slate-500">49002010998</span>
				</div>
			</div>
		</div>
	</div>
</div>
