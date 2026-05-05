import { Bar, BarChart, Cell, Pie, PieChart, ResponsiveContainer, Tooltip, XAxis, YAxis } from 'recharts';

const colors = ['#2563eb', '#0f766e', '#d97706', '#b91c1c', '#7c3aed', '#475569'];

export default function ChartPanel({ title, data = {}, type = 'bar' }) {
  const rows = Object.entries(data || {}).map(([name, value]) => ({ name: name.replaceAll('_', ' '), value }));

  return (
    <section className="chart-card">
      <h2>{title}</h2>
      <ResponsiveContainer width="100%" height={240}>
        {type === 'pie' ? (
          <PieChart>
            <Pie data={rows} dataKey="value" nameKey="name" outerRadius={85} label>
              {rows.map((_, index) => <Cell key={index} fill={colors[index % colors.length]} />)}
            </Pie>
            <Tooltip />
          </PieChart>
        ) : (
          <BarChart data={rows}>
            <XAxis dataKey="name" tick={{ fontSize: 11 }} />
            <YAxis allowDecimals={false} />
            <Tooltip />
            <Bar dataKey="value" fill="#2563eb" radius={[5, 5, 0, 0]} />
          </BarChart>
        )}
      </ResponsiveContainer>
    </section>
  );
}
